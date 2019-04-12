package com.kodillalibrary.kodillalibrary.domain.reader;

import com.kodillalibrary.kodillalibrary.domain.rentBooks.RentBooksDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReaderDto {
    private long id;
    private String name;
    private String surname;
    private String dateOfAccountCreation;
    private List<RentBooksDto> rentBooksDtoList;

    public ReaderDto(long id, String name, String surname, String date){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dateOfAccountCreation = date;
    }
}
