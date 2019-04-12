package com.kodillalibrary.kodillalibrary.service;

import com.kodillalibrary.kodillalibrary.domain.reader.Reader;
import com.kodillalibrary.kodillalibrary.ReaderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Reader> getReader(Long readerId){
        return readerDao.findById(readerId);
    }

    public Optional<Reader> findReaderByNameAndSurname(String name, String surname){
       return readerDao.findByNameAndSurname(name, surname);
    }

    public void deleteReaderByNameAndSurname(String name, String surname){
        readerDao.deleteByNameAndSurname(name, surname);
    }

    public void deleteReaderById(Long readerId){
        readerDao.delete(readerId);
    }
}
