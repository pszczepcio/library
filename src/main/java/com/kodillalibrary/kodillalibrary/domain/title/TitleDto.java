package com.kodillalibrary.kodillalibrary.domain.title;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TitleDto {
    private long id;
    private String title;
    private String author;
    private int yearOfPublishment;

    public TitleDto(String title, String author, int yearOfPublishment) {
        this.title = title;
        this.author = author;
        this.yearOfPublishment = yearOfPublishment;
    }
}
