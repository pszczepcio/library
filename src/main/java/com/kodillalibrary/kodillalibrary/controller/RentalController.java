package com.kodillalibrary.kodillalibrary.controller;

import com.kodillalibrary.kodillalibrary.domain.booksRental.RentalBooksDto;
import com.kodillalibrary.kodillalibrary.domain.copiesOfBooks.CopiesOfBooksDto;
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
    private CopyController copyController;

    @Autowired
    private RentalService serviceRentBooks;

    @Autowired
    private RentBooksMapper rentBooksMapper;

    @RequestMapping(method = RequestMethod.POST, value = "createRentBook")
    public String createRentBook(@RequestBody RentalBooksDto rentalBooksDto) throws ReaderNotFoundException, CopiesOfBookNotFoundException {
        if(copyController.getCopyById(rentalBooksDto.getCopiesOfBooksId()).getStatus().equals("available")) {
            copyController.updateCopy(new CopiesOfBooksDto(
                    copyController.getCopyById(rentalBooksDto.getCopiesOfBooksId()).getId(),
                    "not available",
                    copyController.getCopyById(rentalBooksDto.getCopiesOfBooksId()).getTitleId()
            ));
            copyController.getCopyById(rentalBooksDto.getCopiesOfBooksId()).setStatus("not available");
            serviceRentBooks.saveRent(rentBooksMapper.mapToRentBooks(rentalBooksDto));
            return "Order status: completed";
        }else {
            System.out.println("This book isn't available.");
            return "No copy available. You can not process the order.";
        }
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
    public void deleteRentBookById(@RequestParam long rentBookId) throws RentBooksNotFoundException, CopiesOfBookNotFoundException {
        copyController.updateCopy(new CopiesOfBooksDto(
                copyController.getCopyById(getRentBookById(rentBookId).getCopiesOfBooksId()).getId(),
                "available",
                copyController.getCopyById(getRentBookById(rentBookId).getCopiesOfBooksId()).getTitleId()
        ));
        copyController.getCopyById(getRentBookById(rentBookId).getCopiesOfBooksId()).setStatus("available");
        serviceRentBooks.deleteById(rentBookId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateRentBook")
    public RentalBooksDto updateRental(@RequestBody RentalBooksDto rentalBooksDto) throws ReaderNotFoundException, CopiesOfBookNotFoundException, ParseException {
        return rentBooksMapper.mapToRentBookDto(serviceRentBooks.saveRent(rentBooksMapper.mapToRentBooksAllParam(rentalBooksDto)));
    }

}
