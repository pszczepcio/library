package com.kodillalibrary.kodillalibrary.domain.reader;

import com.kodillalibrary.kodillalibrary.domain.booksRental.RentalBooks;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@NoArgsConstructor
@Entity
@Table(name = "READERS")
public class Reader {
    private long id;
    private String name;
    private String surname;
    private Date dateOfAccountCreation;
    private List<RentalBooks> rentalBooksList = new ArrayList<>();

    public Reader(String name, String surname){
        this.name = name;
        this.surname = surname;
        this.dateOfAccountCreation = new Date();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true)
    public long getId() {
        return id;
    }

    @NotNull
    @Column(name = "NAME", unique = true)
    public String getName() {
        return name;
    }

    @NotNull
    @Column(name = "SURNAME", unique = true)
    public String getSurname() {
        return surname;
    }

    @NotNull
    @Column(name = "DATE_OF_ACCOUNT_CREATE")
    public Date getDateOfAccountCreation() {
        return dateOfAccountCreation;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setDateOfAccountCreation(Date dateOfAccountCreation) {
        this.dateOfAccountCreation = dateOfAccountCreation;
    }

    @OneToMany(
            targetEntity = RentalBooks.class,
            mappedBy = "reader",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    public List<RentalBooks> getRentalBooksList() {
        return rentalBooksList;
    }

    public void setRentalBooksList(List<RentalBooks> rentalBooksList) {
        this.rentalBooksList = rentalBooksList;
    }
}
