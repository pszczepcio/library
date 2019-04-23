package com.kodillalibrary.kodillalibrary.domain.reader;

import com.kodillalibrary.kodillalibrary.domain.booksRental.RentalBooksDto;
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
    private List<RentalBooksDto> rentalBooksDtoList;

    public ReaderDto(String name, String surname){
        this.name = name;
        this.surname = surname;
    }
}
