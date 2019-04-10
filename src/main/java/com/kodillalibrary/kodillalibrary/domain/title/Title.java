package com.kodillalibrary.kodillalibrary.domain.title;

//import com.kodillalibrary.kodillalibrary.domain.copiesOfBooks.CopiesOfBooks;
import com.kodillalibrary.kodillalibrary.domain.copiesOfBooks.CopiesOfBooks;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Table(name = "TITLES")
public class Title {
    private long id;
    private String title;
    private String author;
    private int yearOfPublishment;
    private List<CopiesOfBooks> copiesOfBooksList = new ArrayList<>();

    public Title(String title, String author, int yearOfPublishment) {
        this.title = title;
        this.author = author;
        this.yearOfPublishment = yearOfPublishment;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true)
    public long getId() {
        return id;
    }

    @Column(name = "TITLE", unique = true)
    public String getTitle() {
        return title;
    }

    @Column(name = "AUTHOR")
    public String getAuthor() {
        return author;
    }

    @Column(name = "YEAR_OF_PUBLISHMENT")
    public int getYearOfPublishment() {
        return yearOfPublishment;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYearOfPublishment(int yearOfPublishment) {
        this.yearOfPublishment = yearOfPublishment;
    }

        @OneToMany(
            targetEntity = CopiesOfBooks.class,
            mappedBy = "title",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    public List<CopiesOfBooks> getCopiesOfBooksList() {
        return copiesOfBooksList;
    }

    public void setCopiesOfBooksList(List<CopiesOfBooks> copiesOfBooksList) {
        this.copiesOfBooksList = copiesOfBooksList;
    }
}
