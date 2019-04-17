package com.kodillalibrary.kodillalibrary.controller;

import com.kodillalibrary.kodillalibrary.domain.booksRental.RentalBooksDto;
import com.kodillalibrary.kodillalibrary.exception.CopiesOfBookNotFoundException;
import com.kodillalibrary.kodillalibrary.exception.ReaderNotFoundException;
import com.kodillalibrary.kodillalibrary.exception.RentBooksNotFoundException;
import com.kodillalibrary.kodillalibrary.mappers.RentBooksMapper;
import com.kodillalibrary.kodillalibrary.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/library")
public class RentalController {

    @Autowired
    private RentalService serviceRentBooks;

    @Autowired
    private RentBooksMapper rentBooksMapper;

    @RequestMapping(method = RequestMethod.POST, value = "createRentBook")
    public void createRentBook(@RequestBody RentalBooksDto rentalBooksDto) throws ReaderNotFoundException, CopiesOfBookNotFoundException {
        serviceRentBooks.saveRent(rentBooksMapper.mapToRentBooks(rentalBooksDto));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getRentBooks")
    public List<RentalBooksDto> rentBooksDtoList(){
        return rentBooksMapper.rentBooksDtoList(serviceRentBooks.getRentBooks());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getRentBookById")
    public RentalBooksDto getRentBookById(@RequestParam long rentBookId) throws RentBooksNotFoundException {
        return rentBooksMapper.mapToRentBookDto(serviceRentBooks.getRentBook(rentBookId).orElseThrow(RentBooksNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteRentBookById")
    public void deleteRentBookById(@RequestParam long rentBookId){
        serviceRentBooks.deleteById(rentBookId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateRentBook")
    public RentalBooksDto updateRental(@RequestBody RentalBooksDto rentalBooksDto) throws ReaderNotFoundException, CopiesOfBookNotFoundException, ParseException {
        return rentBooksMapper.mapToRentBookDto(serviceRentBooks.saveRent(rentBooksMapper.mapToRentBooksAllParam(rentalBooksDto)));
    }

}
