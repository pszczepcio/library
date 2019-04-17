package com.kodillalibrary.kodillalibrary.repository;


import com.kodillalibrary.kodillalibrary.domain.reader.Reader;
import com.kodillalibrary.kodillalibrary.repository.reader.dao.ReaderDao;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReaderRepositoryTestSuite {

    @Autowired
    private ReaderDao readerDao;

    @Test
    public void testSaveReader(){
        //Given
        Reader janKowalski = new Reader("Jan","Kowalski");

        //When
        readerDao.save(janKowalski);
        long janKowalskiId = janKowalski.getId();
        System.out.println("ID: " + janKowalskiId);

        //Then
        assertEquals("Jan", readerDao.findById(janKowalskiId).get().getName());
        assertEquals("Kowalski", readerDao.findById(janKowalskiId).get().getSurname());

        //Clean up
        readerDao.delete(janKowalskiId);
    }

    @Test
    public void testFindAll(){
        //Given
        List<Reader> readerList;
        Reader janKowalski = new Reader("Jan","Kowalski");
        Reader dorotaKowalska = new Reader("Dorota", "Kowalska");

        //When
        readerDao.save(janKowalski);
        readerDao.save(dorotaKowalska);
        readerList = readerDao.findAll();

        //Then
        assertEquals(2, readerList.size());
        assertEquals("Jan", readerList.get(0).getName());
        assertEquals("Dorota", readerList.get(1).getName());

        //Clean up
        readerDao.delete(dorotaKowalska.getId());
        readerDao.delete(janKowalski.getId());
    }

    @Test
    public void testFindById(){
        //Given
        Reader janKowalski = new Reader("Jan", "Kowalski");
        Reader dorotaKowalska = new Reader("Dorota", "Kowalska");
        Reader wacekPlacek = new Reader("Wacek", "Placek");

        //When
        readerDao.save(janKowalski);
        readerDao.save(dorotaKowalska);
        readerDao.save(wacekPlacek);
        long janKowalskiId = janKowalski.getId();
        long dorotaKowalskaId = dorotaKowalska.getId();
        long wacekPlacekId = wacekPlacek.getId();

        //Then
        assertEquals("Jan", readerDao.findById(janKowalskiId).get().getName());
        assertEquals("Kowalski", readerDao.findById(janKowalskiId).get().getSurname());
        assertEquals("Dorota", readerDao.findById(dorotaKowalskaId).get().getName());
        assertEquals("Kowalska", readerDao.findById(dorotaKowalskaId).get().getSurname());
        assertEquals("Wacek", readerDao.findById(wacekPlacekId).get().getName());
        assertEquals("Placek", readerDao.findById(wacekPlacekId).get().getSurname());

        //Clean up
        readerDao.delete(wacekPlacekId);
        readerDao.delete(dorotaKowalskaId);
        readerDao.delete(janKowalskiId);
    }

    @Test
    public void testFindByNameAndSurname(){
        //Given
        Reader janKowalski = new Reader("Jan", "Kowalski");
        Reader dorotaKowalska = new Reader("Dorota", "Kowalska");
        Reader wacekPlacek = new Reader("Wacek", "Placek");

        //When
        readerDao.save(janKowalski);
        readerDao.save(dorotaKowalska);
        readerDao.save(wacekPlacek);
        long janKowalskiId = janKowalski.getId();
        long dorotaKowalskaId = dorotaKowalska.getId();
        long wacekPlacekId = wacekPlacek.getId();
        String name = readerDao.findByNameAndSurname("Dorota", "Kowalska").get().getName();
        String surname = readerDao.findByNameAndSurname("Dorota", "Kowalska").get().getSurname();
        long id = readerDao.findByNameAndSurname("Dorota", "Kowalska").get().getId();
        System.out.println("ID: " + janKowalskiId + dorotaKowalskaId + wacekPlacekId);

        //Then
        assertEquals("Dorota", name);
        assertEquals("Kowalska", surname);
        //Clean up
        readerDao.delete(wacekPlacekId);
        readerDao.delete(dorotaKowalskaId);
        readerDao.delete(janKowalskiId);
    }

    @Test
    public void testDeleteByNameAndSurname(){
        //Given
        List<Reader> readerList = new ArrayList<>();
        Reader janKowalski = new Reader("Jan", "Kowalski");
        Reader dorotaKowalska = new Reader("Dorota", "Kowalska");
        Reader wacekPlacek = new Reader("Wacek", "Placek");

        //When
        readerDao.save(janKowalski);
        readerDao.save(dorotaKowalska);
        readerDao.save(wacekPlacek);
        readerList = readerDao.findAll();

        //Then
        assertEquals(3, readerList.size());
        readerDao.deleteByNameAndSurname("Wacek", "Placek");
        readerDao.deleteByNameAndSurname("Dorota", "Kowalska");
        readerDao.deleteByNameAndSurname("Jan", "Kowalski");
        readerList = readerDao.findAll();
        assertEquals(0, readerList.size());
    }
}
