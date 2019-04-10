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
    private long id;
    private String status;
    private long titleId;
    private List<RentBooks> rentBooksList = new ArrayList<>();

    public CopiesOfBooksDto(String status, Integer titleId) {
        this.status = status;
        this.titleId = titleId;
    }
}
