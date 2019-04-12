package com.kodillalibrary.kodillalibrary.domain.rentBooks;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RentBooksDto {
    private long id;
    private long copiesOfBooksId;
    private long readerId;
    private String dateOfRent;
    private String dateOfReturn;

    public RentBooksDto(long copiesOfBooksId, long readerId) {
        this.copiesOfBooksId = copiesOfBooksId;
        this.readerId = readerId;
    }
}
