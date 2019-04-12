package com.kodillalibrary.kodillalibrary.domain.copiesOfBooks;

import com.kodillalibrary.kodillalibrary.domain.rentBooks.RentBooks;
import com.kodillalibrary.kodillalibrary.domain.title.Title;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Table(name = "COPIES_OF_BOOKS")
public class CopiesOfBooks {
    private long id;
    private String status;
    private Title title;
    private List<RentBooks> rentBooksList = new ArrayList<>();

    public CopiesOfBooks(String status) {
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "ID", unique = true)
    public long getId() {
        return id;
    }

    @Column(name = "STATUS")
    public String getStatus() {
        return status;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @ManyToOne
    @JoinColumn(name = "TITLE_ID")
    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    @OneToMany(
            targetEntity = RentBooks.class,
            mappedBy = "copiesOfBooks",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    public List<RentBooks> getRentBooksList() {
        return rentBooksList;
    }

    public void setRentBooksList(List<RentBooks> rentBooksList) {
        this.rentBooksList = rentBooksList;
    }
}
