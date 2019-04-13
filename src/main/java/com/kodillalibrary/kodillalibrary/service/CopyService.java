package com.kodillalibrary.kodillalibrary.service;

import com.kodillalibrary.kodillalibrary.domain.copiesOfBooks.CopiesOfBooks;
import com.kodillalibrary.kodillalibrary.repository.copy.dao.CopyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CopyService {
    @Autowired
    private CopyDao copyDao;
    @Autowired
    private TitleService serviceTitle;

    public CopiesOfBooks save(final CopiesOfBooks copiesOfBooks){
        copiesOfBooks.setTitle(serviceTitle.getBookById(copiesOfBooks.getTitle().getId()));
        return copyDao.save(copiesOfBooks);
    }

    public List<CopiesOfBooks> getAllCopies(){
        return copyDao.findAll();
    }

    public Optional<CopiesOfBooks> getCopyById(final Long id){
        return copyDao.findById(id);
    }

    public void deleteById(Long id){
        copyDao.delete(id);
    }
}
