package com.kodillalibrary.kodillalibrary.service;

import com.kodillalibrary.kodillalibrary.domain.copiesOfBooks.CopiesOfBooks;
import com.kodillalibrary.kodillalibrary.repository.CopiesOfBookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DbServiceCopyBook {
    @Autowired
    private CopiesOfBookDao copiesOfBookDao;

    public CopiesOfBooks createCopies(final CopiesOfBooks copiesOfBooks){
        return copiesOfBookDao.save(copiesOfBooks);
    }

    public List<CopiesOfBooks> getAllCopies(){
        return copiesOfBookDao.findAll();
    }

    public CopiesOfBooks getCopyById(final Integer id){
        return copiesOfBookDao.findById(id);
    }

    public void deleteById(Integer id){
        copiesOfBookDao.delete(id);
    }
}
