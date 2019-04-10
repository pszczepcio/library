package com.kodillalibrary.kodillalibrary.domain.reader;

import com.kodillalibrary.kodillalibrary.domain.rentBooks.RentBooks;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReaderDto {
    private long id;
    private String name;
    private String surname;
    private Date dateOfAccountCreation;
    private List<RentBooks> rentBooksList = new ArrayList<>();

    public ReaderDto(String name, String surname){
        this.name = name;
        this.surname = surname;
        this.dateOfAccountCreation = new Date();
    }
}
