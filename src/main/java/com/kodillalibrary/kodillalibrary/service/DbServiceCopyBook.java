package com.kodillalibrary.kodillalibrary.service;

import com.kodillalibrary.kodillalibrary.domain.copiesOfBooks.CopiesOfBooks;
import com.kodillalibrary.kodillalibrary.CopiesOfBookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DbServiceCopyBook {
    @Autowired
    private CopiesOfBookDao copiesOfBookDao;
    @Autowired
    private DbServiceTitle serviceTitle;

    public CopiesOfBooks save(final CopiesOfBooks copiesOfBooks){
        copiesOfBooks.setTitle(serviceTitle.getBookById(copiesOfBooks.getTitle().getId()));
        return copiesOfBookDao.save(copiesOfBooks);
    }

    public List<CopiesOfBooks> getAllCopies(){
        return copiesOfBookDao.findAll();
    }

    public Optional<CopiesOfBooks> getCopyById(final Long id){
        return copiesOfBookDao.findById(id);
    }

    public void deleteById(Long id){
        copiesOfBookDao.delete(id);
    }
}
