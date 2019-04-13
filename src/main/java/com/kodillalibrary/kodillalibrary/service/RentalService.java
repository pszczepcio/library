package com.kodillalibrary.kodillalibrary.service;

import com.kodillalibrary.kodillalibrary.domain.booksRental.RentalBooks;
import com.kodillalibrary.kodillalibrary.repository.rentbook.dao.RentalDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentalService {

    @Autowired
    private RentalDao rentalDao;

    public RentalBooks saveRent(final RentalBooks rentalBooks){
        return rentalDao.save(rentalBooks);
    }

    public List<RentalBooks> getRentBooks(){
           return rentalDao.findAll();
    }

    public Optional<RentalBooks> getRentBook(long rentBookId){
        return rentalDao.findById(rentBookId);
    }

    public void deleteById(long rentBookId){
        rentalDao.delete(rentBookId);
    }
}
