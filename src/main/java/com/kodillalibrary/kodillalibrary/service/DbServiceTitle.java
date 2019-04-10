package com.kodillalibrary.kodillalibrary.service;

import com.kodillalibrary.kodillalibrary.domain.title.Title;
import com.kodillalibrary.kodillalibrary.repository.TitleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DbServiceTitle {
    @Autowired
    private TitleDao titleDao;

    public Title saveBook(final Title title){
        return titleDao.save(title);
    }

    public List<Title> getAllBooks(){
        return titleDao.findAll();
    }

    public Title getBookById(Long id){
        return titleDao.findById(id);
    }

    public void deleteBookByTitle(String title){
        titleDao.deleteByTitle(title);
    }

    public void deleteBookById(Long id){
        titleDao.delete(id);
    }
}
