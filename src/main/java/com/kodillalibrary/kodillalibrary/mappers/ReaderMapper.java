package com.kodillalibrary.kodillalibrary.mappers;

import com.kodillalibrary.kodillalibrary.domain.reader.Reader;
import com.kodillalibrary.kodillalibrary.domain.reader.ReaderDto;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReaderMapper {
    public Reader mapToReader(final ReaderDto readerDto){
        return new Reader(
                readerDto.getName(),
                readerDto.getSurname()
        );
    }

    public ReaderDto mapToReaderDto(final Reader reader){
        ReaderDto readerDto = new ReaderDto(
                reader.getName(),
                reader.getSurname()
        );
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(reader.getDateOfAccountCreation());
        readerDto.setDateOfAccountCreation(calendar.getTime());
        readerDto.setId(reader.getId());
        return readerDto;
    }

    public List<ReaderDto> mapToReaderDtoList(final List<Reader> readerList){
        return readerList.stream()
                .map(r -> new ReaderDto(
                r.getId(),
                r.getName(),
                r.getSurname(),
                r.getDateOfAccountCreation(),
                r.getRentBooksList()))
                .collect(Collectors.toList());
    }
}
