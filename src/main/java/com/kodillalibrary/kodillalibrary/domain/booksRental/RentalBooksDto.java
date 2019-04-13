package com.kodillalibrary.kodillalibrary.domain.booksRental;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RentalBooksDto {
    private long id;
    private long copiesOfBooksId;
    private long readerId;
    private String dateOfRent;
    private String dateOfReturn;

    public RentalBooksDto(long copiesOfBooksId, long readerId) {
        this.copiesOfBooksId = copiesOfBooksId;
        this.readerId = readerId;
    }
}
