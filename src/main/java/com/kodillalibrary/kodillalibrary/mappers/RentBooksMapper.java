package com.kodillalibrary.kodillalibrary.mappers;

import com.kodillalibrary.kodillalibrary.domain.rentBooks.RentBooks;
import com.kodillalibrary.kodillalibrary.domain.rentBooks.RentBooksDto;
import com.kodillalibrary.kodillalibrary.exception.CopiesOfBookNotFoundException;
import com.kodillalibrary.kodillalibrary.exception.ReaderNotFoundException;
import com.kodillalibrary.kodillalibrary.service.DbServiceCopyBook;
import com.kodillalibrary.kodillalibrary.service.DbServiceReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RentBooksMapper {

    @Autowired
    private DbServiceCopyBook dbServiceCopyBook;

    @Autowired
    private DbServiceReader dbServiceReader;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public RentBooks mapToRentBooks(final RentBooksDto rentBooksDto) throws CopiesOfBookNotFoundException, ReaderNotFoundException{
        return new RentBooks (
                dbServiceCopyBook.getCopyById(rentBooksDto.getCopiesOfBooksId()).orElseThrow(CopiesOfBookNotFoundException::new),
                dbServiceReader.getReader(rentBooksDto.getReaderId()).orElseThrow(ReaderNotFoundException::new));

    }

    public RentBooks mapToRentBooksAllParam(final RentBooksDto rentBooksDto) throws CopiesOfBookNotFoundException, ReaderNotFoundException, ParseException {
        return new RentBooks(
                rentBooksDto.getId(),
                dateFormat.parse(rentBooksDto.getDateOfRent()),
                dateFormat.parse(rentBooksDto.getDateOfReturn()),
                dbServiceCopyBook.getCopyById(rentBooksDto.getCopiesOfBooksId()).orElseThrow(CopiesOfBookNotFoundException::new),
                dbServiceReader.getReader(rentBooksDto.getReaderId()).orElseThrow(ReaderNotFoundException::new)
        );
    }

    public RentBooksDto mapToRentBookDto(final RentBooks rentBooks){
        return new RentBooksDto(
                rentBooks.getId(),
                rentBooks.getCopiesOfBooks().getId(),
                rentBooks.getReader().getId(),
                dateFormat.format(rentBooks.getDateOfRent()),
                dateFormat.format(rentBooks.getDateOfReturn())
        );
    }

    public List<RentBooksDto> rentBooksDtoList(final List<RentBooks> rentBooksList){
        return rentBooksList.stream()
                .map(r -> new RentBooksDto(
                        r.getId(),
                        r.getCopiesOfBooks().getId(),
                        r.getReader().getId(),
                        dateFormat.format(r.getDateOfRent()),
                        dateFormat.format(r.getDateOfReturn())))
                .collect(Collectors.toList());
    }
}
