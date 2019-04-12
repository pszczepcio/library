package com.kodillalibrary.kodillalibrary.service;

import com.kodillalibrary.kodillalibrary.domain.rentBooks.RentBooks;
import com.kodillalibrary.kodillalibrary.RentBooksDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DbServiceRentBooks {

    @Autowired
    private RentBooksDao rentBooksDao;

    public RentBooks saveRent(final  RentBooks rentBooks){
        return rentBooksDao.save(rentBooks);
    }

    public List<RentBooks> getRentBooks(){
           return rentBooksDao.findAll();
    }

    public Optional<RentBooks> getRentBook(long rentBookId){
        return rentBooksDao.findById(rentBookId);
    }

    public void deleteById(long rentBookId){
        rentBooksDao.delete(rentBookId);
    }
}
