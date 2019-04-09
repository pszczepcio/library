package com.kodillalibrary.kodillalibrary.service;

import com.kodillalibrary.kodillalibrary.domain.reader.Reader;
import com.kodillalibrary.kodillalibrary.repository.ReaderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DbServiceReader {
    @Autowired
    private ReaderDao readerDao;

    public Reader saveReaders(final Reader reader){
        return readerDao.save(reader);
    }

    public List<Reader> getAllReader(){
        return readerDao.findAll();
    }

    public Reader getReader(Integer readerId){
        return readerDao.findOne(readerId);
    }

    public Reader findReaderByNameAndSurname(String name, String surname){
       return readerDao.findByNameAndSurname(name, surname);
    }

    public void deleteReaderByNameAndSurname(String name, String surname){
        readerDao.deleteByNameAndSurname(name, surname);
    }

    public void deleteReaderById(Integer readerId){
        readerDao.delete(readerId);
    }
}
