package com.kodillalibrary.kodillalibrary.domain.copiesOfBooks;

import com.kodillalibrary.kodillalibrary.domain.rentBooks.RentBooks;
import com.kodillalibrary.kodillalibrary.domain.rentBooks.RentBooksDto;
import com.kodillalibrary.kodillalibrary.domain.title.Title;
import com.kodillalibrary.kodillalibrary.domain.title.TitleDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CopiesOfBooksDto {
    private int id;
    private String status;
    private TitleDto titleDto;
}