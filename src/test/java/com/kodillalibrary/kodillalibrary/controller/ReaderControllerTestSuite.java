package com.kodillalibrary.kodillalibrary.controller;

import com.kodillalibrary.kodillalibrary.domain.reader.Reader;
import com.kodillalibrary.kodillalibrary.domain.reader.ReaderDto;
import com.kodillalibrary.kodillalibrary.exception.ReaderNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReaderControllerTestSuite {

    @Autowired
    private ReaderController readerController;

    @Test
    public void shouldCreateReader() throws ReaderNotFoundException {
        //Given
        ReaderDto readerDto = new ReaderDto( "name", "surname");

        //When
        readerController.createReader(readerDto);
        long readerId = readerController.getReader("name", "surname").getId();
        String date = readerController.getReader("name", "surname").getDateOfAccountCreation();

        //Then
        assertEquals("name", readerController.getReaderById(readerId).getName());
        assertEquals("surname", readerController.getReaderById(readerId).getSurname());
        assertTrue(date != null);

        //Clean up
        readerController.deleteReaderById(readerId);
    }

    @Test
    public void shouldGetAllReaders(){
        //Given
        ReaderDto readerDto = new ReaderDto( "name", "surname");
        ReaderDto readerDto1 = new ReaderDto( "name1", "surname1");
        ReaderDto readerDto2 = new ReaderDto( "name2", "surname2");

        //When
        readerController.createReader(readerDto);
        readerController.createReader(readerDto1);
        readerController.createReader(readerDto2);

        List<ReaderDto> readerDtoList = readerController.getAllReaders();

        //Then
        assertEquals(3, readerController.getAllReaders().size());

        //Clean up
        readerController.deleteReaderById(readerDtoList.get(2).getId());
        readerController.deleteReaderById(readerDtoList.get(1).getId());
        readerController.deleteReaderById(readerDtoList.get(0).getId());
    }

    @Test
    public void shouldGetReader() throws ReaderNotFoundException {
        //Given
        ReaderDto readerDto = new ReaderDto( "name", "surname");

        //When
        readerController.createReader(readerDto);


        //Then
        assertEquals("name", readerController.getReader("name", "surname").getName());
        assertEquals("surname", readerController.getReader("name", "surname").getSurname());

        //Clean up
        readerController.deleteReaderById(readerController.getReader("name", "surname").getId());
    }

    @Test
    public void shouldGetReaderById() throws ReaderNotFoundException {
        //Given
        ReaderDto readerDto1 = new ReaderDto("name1", "surname1");
        ReaderDto readerDto2 = new ReaderDto("name2", "surname2");

        //When
        readerController.createReader(readerDto1);
        readerController.createReader(readerDto2);
        List<ReaderDto> readerDtoList = readerController.getAllReaders();

        //Then
        assertEquals("name1", readerController.getReaderById(readerDtoList.get(0).getId()).getName());
        assertEquals("surname1", readerController.getReaderById(readerDtoList.get(0).getId()).getSurname());
        assertEquals("name2", readerController.getReaderById(readerDtoList.get(1).getId()).getName());
        assertEquals("surname2", readerController.getReaderById(readerDtoList.get(1).getId()).getSurname());


        readerController.deleteReaderByNameAndSurname("name1", "surname1");
        readerController.deleteReaderByNameAndSurname("name2", "surname2");
    }
}
