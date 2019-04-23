package com.kodillalibrary.kodillalibrary.domain.booksRental;

import com.kodillalibrary.kodillalibrary.domain.copiesOfBooks.CopiesOfBooks;
import com.kodillalibrary.kodillalibrary.domain.reader.Reader;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "RENT_BOOKS")
public class RentalBooks {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    private long id;

    @NotNull
    @Column(name = "DATE_OF_RENT")
    private Date dateOfRent;

    @NotNull
    @Column(name = "DATE_OF_RETURN")
    private Date dateOfReturn;

    @ManyToOne()
    @JoinColumn(name = "ID_COPIES_OF_BOOKS")
    private CopiesOfBooks copiesOfBooks;

    @ManyToOne
    @JoinColumn(name = "ID_READER")
    private Reader reader;
    private final static int NEXTMONTH = 30;

    public RentalBooks(CopiesOfBooks copiesOfBooks, Reader reader){
        this.copiesOfBooks = copiesOfBooks;
        this.reader = reader;
        this.dateOfRent = new Date();
        Calendar returnBook = Calendar.getInstance();
        returnBook.setTime((Date) dateOfRent.clone());
        returnBook.add(Calendar.DAY_OF_MONTH, NEXTMONTH);
        this.dateOfReturn = returnBook.getTime();
    }
}
