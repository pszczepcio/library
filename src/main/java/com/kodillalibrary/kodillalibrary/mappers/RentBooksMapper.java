package com.kodillalibrary.kodillalibrary.mappers;

import com.kodillalibrary.kodillalibrary.domain.booksRental.RentalBooks;
import com.kodillalibrary.kodillalibrary.domain.booksRental.RentalBooksDto;
import com.kodillalibrary.kodillalibrary.exception.CopiesOfBookNotFoundException;
import com.kodillalibrary.kodillalibrary.exception.ReaderNotFoundException;
import com.kodillalibrary.kodillalibrary.service.CopyService;
import com.kodillalibrary.kodillalibrary.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RentBooksMapper {

    @Autowired
    private CopyService copyService;

    @Autowired
    private ReaderService readerService;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public RentalBooks mapToRentBooks(final RentalBooksDto rentalBooksDto) throws CopiesOfBookNotFoundException, ReaderNotFoundException{
        return new RentalBooks(
                copyService.getCopyById(rentalBooksDto.getCopiesOfBooksId()).orElseThrow(CopiesOfBookNotFoundException::new),
                readerService.getReader(rentalBooksDto.getReaderId()).orElseThrow(ReaderNotFoundException::new));

    }

    public RentalBooks mapToRentBooksAllParam(final RentalBooksDto rentalBooksDto) throws CopiesOfBookNotFoundException, ReaderNotFoundException, ParseException {
        return new RentalBooks(
                rentalBooksDto.getId(),
                dateFormat.parse(rentalBooksDto.getDateOfRent()),
                dateFormat.parse(rentalBooksDto.getDateOfReturn()),
                copyService.getCopyById(rentalBooksDto.getCopiesOfBooksId()).orElseThrow(CopiesOfBookNotFoundException::new),
                readerService.getReader(rentalBooksDto.getReaderId()).orElseThrow(ReaderNotFoundException::new)
        );
    }

    public RentalBooksDto mapToRentBookDto(final RentalBooks rentalBooks){
        return new RentalBooksDto(
                rentalBooks.getId(),
                rentalBooks.getCopiesOfBooks().getId(),
                rentalBooks.getReader().getId(),
                dateFormat.format(rentalBooks.getDateOfRent()),
                dateFormat.format(rentalBooks.getDateOfReturn())
        );
    }

    public List<RentalBooksDto> rentBooksDtoList(final List<RentalBooks> rentalBooksList){
        return rentalBooksList.stream()
                .map(r -> new RentalBooksDto(
                        r.getId(),
                        r.getCopiesOfBooks().getId(),
                        r.getReader().getId(),
                        dateFormat.format(r.getDateOfRent()),
                        dateFormat.format(r.getDateOfReturn())))
                .collect(Collectors.toList());
    }
}
