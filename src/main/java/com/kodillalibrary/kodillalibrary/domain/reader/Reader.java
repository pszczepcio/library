package com.kodillalibrary.kodillalibrary.domain.reader;

import com.kodillalibrary.kodillalibrary.domain.booksRental.RentalBooks;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "READERS")
@Getter
@Setter
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true)
    private long id;

    @NotNull
    @Column(name = "NAME", unique = true)
    private  String name;

    @NotNull
    @Column(name = "SURNAME", unique = true)
    private String surname;

    @NotNull
    @Column(name = "DATE_OF_ACCOUNT_CREATE")
    private Date dateOfAccountCreation;

    @OneToMany(
            targetEntity = RentalBooks.class,
            mappedBy = "reader",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<RentalBooks> rentalBooksList = new ArrayList<>();

    public Reader(String name, String surname){
        this.name = name;
        this.surname = surname;
        this.dateOfAccountCreation = new Date();
    }
}
