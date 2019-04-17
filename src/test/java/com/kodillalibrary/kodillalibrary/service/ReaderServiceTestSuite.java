package com.kodillalibrary.kodillalibrary.service;

import com.kodillalibrary.kodillalibrary.domain.reader.Reader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReaderServiceTestSuite {

    @Autowired
    private ReaderService readerService;

    @Test
    public void shouldSaveReaders(){
        //Given
        Reader janKowalski = new Reader("Jan", "Kowalski");

        //When
        readerService.saveReader(janKowalski);
        long id = janKowalski.getId();

        //Then
        assertEquals("Jan", readerService.getReader(id).get().getName());
        assertEquals("Kowalski", readerService.getReader(id).get().getSurname());

        //Clean up
        readerService.deleteReaderById(id);
    }

    @Test
    public void shouldGetAllReader(){
        //Given
        Reader janKowalski = new Reader("Jan", "Kowalski");
        Reader wacekPlacek = new Reader("Wacek", "Placek");

        //When
        readerService.saveReader(janKowalski);
        readerService.saveReader(wacekPlacek);
        long janKowalskiId = janKowalski.getId();
        long wacekPlacekID = wacekPlacek.getId();

        //Then
        assertEquals("Jan", readerService.getReader(janKowalskiId).get().getName());
        assertEquals("Kowalski", readerService.getReader(janKowalskiId).get().getSurname());
        assertEquals("Wacek", readerService.getReader(wacekPlacekID).get().getName());
        assertEquals("Placek", readerService.getReader(wacekPlacekID).get().getSurname());

        //Clean up
        readerService.deleteReaderById(wacekPlacekID);
        readerService.deleteReaderById(janKowalskiId);
    }

    @Test
    public void shouldGetReader(){
        //Given
        Reader janKowalski = new Reader("Jan", "Kowalski");
        Reader wacekPlacek = new Reader("Wacek", "Placek");

        //When
        readerService.saveReader(janKowalski);
        readerService.saveReader(wacekPlacek);
        long janKowalskiId = janKowalski.getId();
        long wacekPlacekID = wacekPlacek.getId();

        //Then
        assertEquals("Jan", readerService.getReader(janKowalskiId).get().getName());
        assertEquals("Kowalski", readerService.getReader(janKowalskiId).get().getSurname());

        //Clean up

        readerService.deleteReaderById(wacekPlacekID);
        readerService.deleteReaderById(janKowalskiId);
    }

    @Test
    public void shouldFindReaderByNameAndSurname(){
        //Given
        Reader janKowalski = new Reader("Jan", "Kowalski");
        Reader wacekPlacek = new Reader("Wacek", "Placek");

        //When
        readerService.saveReader(janKowalski);
        readerService.saveReader(wacekPlacek);
        long janKowalskiId = janKowalski.getId();
        long wacekPlacekID = wacekPlacek.getId();

        //Then
        assertEquals("Jan", readerService.findReaderByNameAndSurname("Jan", "Kowalski").get().getName());
        assertEquals("Placek", readerService.findReaderByNameAndSurname("Wacek", "Placek").get().getSurname());

        //Clean up

        readerService.deleteReaderById(wacekPlacekID);
        readerService.deleteReaderById(janKowalskiId);
    }

    @Test
    public void shouldDeleteReaderByNameAndSurname(){
        //Given

        Reader janKowalski = new Reader("Jan", "Kowalski");
        Reader wacekPlacek = new Reader("Wacek", "Placek");

        //When
        readerService.saveReader(janKowalski);
        readerService.saveReader(wacekPlacek);
        long janKowalskiId = janKowalski.getId();
        long wacekPlacekID = wacekPlacek.getId();

        //Then
        assertEquals(2, readerService.getAllReader().size());
        readerService.deleteReaderById(wacekPlacekID);
        readerService.deleteReaderById(janKowalskiId);
        assertEquals(0,readerService.getAllReader().size());
    }
}
