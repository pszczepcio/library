package com.kodillalibrary.kodillalibrary.domain.copiesOfBooks;

import com.kodillalibrary.kodillalibrary.domain.booksRental.RentalBooks;
import com.kodillalibrary.kodillalibrary.domain.title.Title;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "COPIES_OF_BOOKS")
public class CopiesOfBooks {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "ID", unique = true)
    private long id;

    @Column(name = "STATUS")
    private String status;

    @ManyToOne
    @JoinColumn(name = "TITLE_ID")
    private Title title;

    @OneToMany(
            targetEntity = RentalBooks.class,
            mappedBy = "copiesOfBooks",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<RentalBooks> rentalBooksList = new ArrayList<>();

    public CopiesOfBooks(String status) {
        this.status = status;
    }
}
