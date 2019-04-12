package com.kodillalibrary.kodillalibrary.domain.copiesOfBooks;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CopiesOfBooksDto {
    private long id;
    private String status;
    private long titleId;
}
