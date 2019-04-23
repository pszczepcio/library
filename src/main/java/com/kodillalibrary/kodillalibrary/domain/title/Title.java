package com.kodillalibrary.kodillalibrary.domain.title;


import com.kodillalibrary.kodillalibrary.domain.copiesOfBooks.CopiesOfBooks;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "TITLES")
public class Title {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true)
    private long id;

    @Column(name = "TITLE", unique = true)
    private String title;

    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "YEAR_OF_PUBLISHMENT")
    private int yearOfPublishment;

    @OneToMany(
            targetEntity = CopiesOfBooks.class,
            mappedBy = "title",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<CopiesOfBooks> copiesOfBooksList = new ArrayList<>();

    public Title(String title, String author, int yearOfPublishment) {
        this.title = title;
        this.author = author;
        this.yearOfPublishment = yearOfPublishment;
    }
}
