package com.kodillalibrary.kodillalibrary.mappers;

import com.kodillalibrary.kodillalibrary.domain.reader.Reader;
import com.kodillalibrary.kodillalibrary.domain.reader.ReaderDto;
import com.kodillalibrary.kodillalibrary.service.DbServiceRentBooks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReaderMapper {

    @Autowired
    private RentBooksMapper rentBooksMapper;

    @Autowired
    private DbServiceRentBooks dbServiceRentBooks;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Reader mapToReader(final ReaderDto readerDto){
        return new Reader(
                readerDto.getName(),
                readerDto.getSurname()
        );
    }

    public ReaderDto mapToReaderDto(final Reader reader){
        return new ReaderDto(
                reader.getId(),
                reader.getName(),
                reader.getSurname(),
                dateFormat.format(reader.getDateOfAccountCreation()),
                rentBooksMapper.rentBooksDtoList(dbServiceRentBooks.getRentBooks().stream()
                        .filter(r -> r.getReader().getId() == reader.getId())
                        .collect(Collectors.toList()))
        );
    }

    public List<ReaderDto> mapToReaderDtoList(final List<Reader> readerList){
        return readerList.stream()
                .map(r -> new ReaderDto(
                        r.getId(),
                        r.getName(),
                        r.getSurname(),
                        dateFormat.format(r.getDateOfAccountCreation()),
                        rentBooksMapper.rentBooksDtoList(dbServiceRentBooks.getRentBooks().stream()
                        .filter(rentBooks -> rentBooks.getReader().getId() == r.getId())
                        .collect(Collectors.toList()))))
                .collect(Collectors.toList());
    }
}
