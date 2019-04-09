package com.kodillalibrary.kodillalibrary.domain.rentBooks;

import com.kodillalibrary.kodillalibrary.domain.copiesOfBooks.CopiesOfBooks;
import com.kodillalibrary.kodillalibrary.domain.reader.Reader;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RentBooksDto {
    private int id;
    private Date dateOfRent;
    private Date dateOfReturn;
    private CopiesOfBooks copiesOfBooks;
    private Reader reader;
}
