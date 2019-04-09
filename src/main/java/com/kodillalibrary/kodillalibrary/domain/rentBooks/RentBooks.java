package com.kodillalibrary.kodillalibrary.domain.rentBooks;

import com.kodillalibrary.kodillalibrary.domain.copiesOfBooks.CopiesOfBooks;
import com.kodillalibrary.kodillalibrary.domain.reader.Reader;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Date;

@NoArgsConstructor
@Entity
@Table(name = "RENT_BOOKS")
public class RentBooks {
    private int id;
    private Date dateOfRent;
    private Date dateOfReturn;
    private CopiesOfBooks copiesOfBooks;
    private Reader reader;
    private final static int NEXTMONTH = 30;

    public RentBooks(int idCopiesOfBooks, int idReader){
        this.dateOfRent = new Date();
        Calendar returnBook = Calendar.getInstance();
        returnBook.setTime((Date) dateOfRent.clone());
        returnBook.add(Calendar.DAY_OF_MONTH, NEXTMONTH);
        this.dateOfReturn = returnBook.getTime();
    }

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    public int getId() {
        return id;
    }

    @NotNull
    @Column(name = "DATE_OF_RENT")
    public Date getDateOfRent() {
        return dateOfRent;
    }

    @NotNull
    @Column(name = "DATE_OF_RETURN")
    public Date getDateOfReturn() {
        return dateOfReturn;
    }

    @ManyToOne()
    @JoinColumn(name = "ID_COPIES_OF_BOOKS")
    public CopiesOfBooks getCopiesOfBooks() {
        return copiesOfBooks;
    }

    public void setCopiesOfBooks(CopiesOfBooks copiesOfBooks) {
        this.copiesOfBooks = copiesOfBooks;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDateOfRent(Date dateOfRent) {
        this.dateOfRent = dateOfRent;
    }

    public void setDateOfReturn(Date dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }

    @ManyToOne
    @JoinColumn(name = "ID_READER")
    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }
}
