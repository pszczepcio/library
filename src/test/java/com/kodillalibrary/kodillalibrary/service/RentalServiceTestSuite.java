package com.kodillalibrary.kodillalibrary.service;

import com.kodillalibrary.kodillalibrary.domain.booksRental.RentalBooks;
import com.kodillalibrary.kodillalibrary.domain.copiesOfBooks.CopiesOfBooks;
import com.kodillalibrary.kodillalibrary.domain.reader.Reader;
import com.kodillalibrary.kodillalibrary.domain.title.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RentalServiceTestSuite {

    @Autowired
    private ReaderService readerService;

    @Autowired
    private TitleService titleService;

    @Autowired
    private CopyService copyService;

    @Autowired
    private RentalService rentalService;

    @Test
    public void shouldSaveRent(){
        //Given
        Reader reader = new Reader("name", "surname");
        readerService.saveReader(reader);

        Title title = new Title("title", "author", 2000);
        CopiesOfBooks copy = new CopiesOfBooks("available");
        copy.setTitle(title);
        title.getCopiesOfBooksList().add(copy);

        RentalBooks rentalBook = new RentalBooks(copy, reader);
        reader.getRentalBooksList().add(rentalBook);
        copy.getRentalBooksList().add(rentalBook);

        //When
        titleService.saveBook(title);
        copyService.save(copy);
        rentalService.saveRent(rentalBook);
        long rentalId = rentalBook.getId();
        long copyId = copy.getId();
        long titleId = title.getId();
        long readerId = reader.getId();

        //Then
        assertEquals("name", rentalService.getRentBook(rentalId).get().getReader().getName());
        assertEquals("surname", rentalService.getRentBook(rentalId).get().getReader().getSurname());
        assertEquals("title", rentalService.getRentBook(rentalId).get().getCopiesOfBooks().getTitle().getTitle());
        assertEquals("author", rentalService.getRentBook(rentalId).get().getCopiesOfBooks().getTitle().getAuthor());
        assertEquals("available", rentalService.getRentBook(rentalId).get().getCopiesOfBooks().getStatus());
        assertTrue(reader.getRentalBooksList().size() > 0);
        assertTrue(copy.getRentalBooksList().size() > 0);

        //Clean up
        rentalService.deleteById(rentalId);
        copyService.deleteById(copyId);
        titleService.deleteBookById(titleId);
        readerService.deleteReaderById(readerId);
    }

    @Test
    public void shouldGetRentBooks(){
        //Given
        Reader reader = new Reader("name", "surname");
        readerService.saveReader(reader);

        Title title1 = new Title("title1", "author1", 2001);
        Title title2 = new Title("title2", "author2", 2002);
        Title title3 = new Title("title3", "author3", 2003);
        CopiesOfBooks copy1 = new CopiesOfBooks("available");
        copy1.setTitle(title1);
        title1.getCopiesOfBooksList().add(copy1);

        CopiesOfBooks copy2 = new CopiesOfBooks("available");
        copy2.setTitle(title2);
        title2.getCopiesOfBooksList().add(copy2);

        CopiesOfBooks copy3 = new CopiesOfBooks("available");
        copy3.setTitle(title3);
        title3.getCopiesOfBooksList().add(copy3);

        RentalBooks rentalBook1 = new RentalBooks(copy1, reader);
        reader.getRentalBooksList().add(rentalBook1);
        copy1.getRentalBooksList().add(rentalBook1);

        RentalBooks rentalBook2 = new RentalBooks(copy2, reader);
        reader.getRentalBooksList().add(rentalBook2);
        copy2.getRentalBooksList().add(rentalBook2);

        RentalBooks rentalBook3 = new RentalBooks(copy3, reader);
        reader.getRentalBooksList().add(rentalBook3);
        copy3.getRentalBooksList().add(rentalBook3);

        //When
        titleService.saveBook(title1);
        titleService.saveBook(title2);
        titleService.saveBook(title3);

        copyService.save(copy1);
        copyService.save(copy2);
        copyService.save(copy3);

        rentalService.saveRent(rentalBook1);
        rentalService.saveRent(rentalBook2);
        rentalService.saveRent(rentalBook3);

        long rental1Id = rentalBook1.getId();
        long rental2Id = rentalBook2.getId();
        long rental3Id = rentalBook3.getId();
        long copy1Id = copy1.getId();
        long copy2Id = copy2.getId();
        long copy3Id = copy3.getId();
        long title1Id = title1.getId();
        long title2Id = title2.getId();
        long title3Id = title3.getId();
        long readerId = reader.getId();

        //Then
        assertEquals(3, rentalService.getRentBooks().size());

        //Clean up
        rentalService.deleteById(rental1Id);
        rentalService.deleteById(rental2Id);
        rentalService.deleteById(rental3Id);
        copyService.deleteById(copy1Id);
        copyService.deleteById(copy2Id);
        copyService.deleteById(copy3Id);
        titleService.deleteBookById(title1Id);
        titleService.deleteBookById(title2Id);
        titleService.deleteBookById(title3Id);
        readerService.deleteReaderById(readerId);
    }

    @Test
    public void shouldGetRentBook(){
        //Given
        Reader reader = new Reader("name", "surname");
        readerService.saveReader(reader);

        Title title1 = new Title("title1", "author1", 2001);
        Title title2 = new Title("title2", "author2", 2002);

        CopiesOfBooks copy1 = new CopiesOfBooks("available");
        copy1.setTitle(title1);
        title1.getCopiesOfBooksList().add(copy1);

        CopiesOfBooks copy2 = new CopiesOfBooks("available");
        copy2.setTitle(title2);
        title2.getCopiesOfBooksList().add(copy2);

        RentalBooks rentalBook1 = new RentalBooks(copy1, reader);
        reader.getRentalBooksList().add(rentalBook1);
        copy1.getRentalBooksList().add(rentalBook1);

        RentalBooks rentalBook2 = new RentalBooks(copy2, reader);
        reader.getRentalBooksList().add(rentalBook2);
        copy2.getRentalBooksList().add(rentalBook2);

        //When
        titleService.saveBook(title1);
        titleService.saveBook(title2);

        copyService.save(copy1);
        copyService.save(copy2);

        rentalService.saveRent(rentalBook1);
        rentalService.saveRent(rentalBook2);

        long rental1Id = rentalBook1.getId();
        long rental2Id = rentalBook2.getId();
        long copy1Id = copy1.getId();
        long copy2Id = copy2.getId();
        long title1Id = title1.getId();
        long title2Id = title2.getId();
        long readerId = reader.getId();

        //Then
        assertEquals("title1", rentalService.getRentBook(rental1Id).get().getCopiesOfBooks().getTitle().getTitle());
        assertEquals("author1", rentalService.getRentBook(rental1Id).get().getCopiesOfBooks().getTitle().getAuthor());
        assertEquals(2001, rentalService.getRentBook(rental1Id).get().getCopiesOfBooks().getTitle().getYearOfPublishment());

        //Clean up
        rentalService.deleteById(rental1Id);
        rentalService.deleteById(rental2Id);
        copyService.deleteById(copy1Id);
        copyService.deleteById(copy2Id);
        titleService.deleteBookById(title1Id);
        titleService.deleteBookById(title2Id);
        readerService.deleteReaderById(readerId);
    }

    @Test
    public void shouldDeleteById(){
        Reader reader = new Reader("name", "surname");
        readerService.saveReader(reader);

        Title title1 = new Title("title1", "author1", 2001);
        Title title2 = new Title("title2", "author2", 2002);

        CopiesOfBooks copy1 = new CopiesOfBooks("available");
        copy1.setTitle(title1);
        title1.getCopiesOfBooksList().add(copy1);

        CopiesOfBooks copy2 = new CopiesOfBooks("available");
        copy2.setTitle(title2);
        title2.getCopiesOfBooksList().add(copy2);

        RentalBooks rentalBook1 = new RentalBooks(copy1, reader);
        reader.getRentalBooksList().add(rentalBook1);
        copy1.getRentalBooksList().add(rentalBook1);

        RentalBooks rentalBook2 = new RentalBooks(copy2, reader);
        reader.getRentalBooksList().add(rentalBook2);
        copy2.getRentalBooksList().add(rentalBook2);

        //When
        titleService.saveBook(title1);
        titleService.saveBook(title2);

        copyService.save(copy1);
        copyService.save(copy2);

        rentalService.saveRent(rentalBook1);
        rentalService.saveRent(rentalBook2);

        long rental1Id = rentalBook1.getId();
        long rental2Id = rentalBook2.getId();
        long copy1Id = copy1.getId();
        long copy2Id = copy2.getId();
        long title1Id = title1.getId();
        long title2Id = title2.getId();
        long readerId = reader.getId();

        //Then
        assertEquals(2, rentalService.getRentBooks().size());
        rentalService.deleteById(rental1Id);
        assertEquals(1, rentalService.getRentBooks().size());
        rentalService.deleteById(rental2Id);
        assertTrue(rentalService.getRentBooks().size() == 0);

        //Clean up
        copyService.deleteById(copy1Id);
        copyService.deleteById(copy2Id);
        titleService.deleteBookById(title1Id);
        titleService.deleteBookById(title2Id);
        readerService.deleteReaderById(readerId);
    }
}
