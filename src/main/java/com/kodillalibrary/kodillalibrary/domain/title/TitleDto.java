package com.kodillalibrary.kodillalibrary.domain.title;

import com.kodillalibrary.kodillalibrary.domain.copiesOfBooks.CopiesOfBooks;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TitleDto {
    private int id;
    private String title;
    private String author;
    private int yearOfPublishment;
    private List<CopiesOfBooks> copiesOfBooksList = new ArrayList<>();

    public TitleDto(String title, String author, int yearOfPublishment) {
        this.title = title;
        this.author = author;
        this.yearOfPublishment = yearOfPublishment;
    }
}
