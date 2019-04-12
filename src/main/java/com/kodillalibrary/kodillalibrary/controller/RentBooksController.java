package com.kodillalibrary.kodillalibrary.controller;

import com.kodillalibrary.kodillalibrary.domain.rentBooks.RentBooksDto;
import com.kodillalibrary.kodillalibrary.exception.CopiesOfBookNotFoundException;
import com.kodillalibrary.kodillalibrary.exception.ReaderNotFoundException;
import com.kodillalibrary.kodillalibrary.exception.RentBooksNotFoundException;
import com.kodillalibrary.kodillalibrary.mappers.RentBooksMapper;
import com.kodillalibrary.kodillalibrary.service.DbServiceRentBooks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/library")
public class RentBooksController {

    @Autowired
    private DbServiceRentBooks serviceRentBooks;

    @Autowired
    private RentBooksMapper rentBooksMapper;

    @RequestMapping(method = RequestMethod.POST, value = "createRentBook")
    public void createRentBook(@RequestBody RentBooksDto rentBooksDto) throws ReaderNotFoundException, CopiesOfBookNotFoundException {
        serviceRentBooks.saveRent(rentBooksMapper.mapToRentBooks(rentBooksDto));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getRentBooks")
    public List<RentBooksDto> rentBooksDtoList(){
        return rentBooksMapper.rentBooksDtoList(serviceRentBooks.getRentBooks());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getRentBookById")
    public RentBooksDto getRentBookById(@RequestParam long rentBookId) throws RentBooksNotFoundException {
        return rentBooksMapper.mapToRentBookDto(serviceRentBooks.getRentBook(rentBookId).orElseThrow(RentBooksNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteRentBookById")
    public void deleteRentBookById(@RequestParam long rentBookId){
        serviceRentBooks.deleteById(rentBookId);
    }

}
