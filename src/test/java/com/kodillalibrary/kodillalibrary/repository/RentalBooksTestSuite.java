package com.kodillalibrary.kodillalibrary.repository;

import com.kodillalibrary.kodillalibrary.domain.booksRental.RentalBooks;
import com.kodillalibrary.kodillalibrary.domain.copiesOfBooks.CopiesOfBooks;
import com.kodillalibrary.kodillalibrary.domain.reader.Reader;
import com.kodillalibrary.kodillalibrary.domain.title.Title;
import com.kodillalibrary.kodillalibrary.repository.copy.dao.CopyDao;
import com.kodillalibrary.kodillalibrary.repository.reader.dao.ReaderDao;
import com.kodillalibrary.kodillalibrary.repository.rentbook.dao.RentalDao;
import com.kodillalibrary.kodillalibrary.repository.title.dao.TitleDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RentalBooksTestSuite {

    @Autowired
    private ReaderDao readerDao;

    @Autowired
    private TitleDao titleDao;

    @Autowired
    private CopyDao copyDao;

    @Autowired
    private RentalDao rentalDao;

    @Test
    public void shouldSave(){
        //Given
        Reader reader = new Reader("name", "surname");
        readerDao.save(reader);
        Title title = new Title("Title", "Author", 2001);
        CopiesOfBooks copy = new CopiesOfBooks("available");
        copy.setTitle(title);
        title.getCopiesOfBooksList().add(copy);
        RentalBooks rentalBooks = new RentalBooks(copy, reader);
        reader.getRentalBooksList().add(rentalBooks);

        //When
        titleDao.save(title);
        copyDao.save(copy);
        rentalDao.save(rentalBooks);

        long readerId = reader.getId();
        long titleId = title.getId();
        long copyId = copy.getId();
        long rentalId = rentalBooks.getId();

        //Then
        assertEquals("name" , rentalDao.findById(rentalId).get().getReader().getName());
        assertEquals("surname" , rentalDao.findById(rentalId).get().getReader().getSurname());
        assertEquals("available" , rentalDao.findById(rentalId).get().getCopiesOfBooks().getStatus());
        assertEquals("Title" , rentalDao.findById(rentalId).get().getCopiesOfBooks().getTitle().getTitle());
        assertEquals("Author" , rentalDao.findById(rentalId).get().getCopiesOfBooks().getTitle().getAuthor());
        assertTrue(reader.getRentalBooksList().size() > 0);

        //Clean up
        rentalDao.delete(rentalId);
        copyDao.delete(copyId);
        titleDao.delete(titleId);
        readerDao.delete(readerId);
    }

    @Test
    public void shouldFindAll(){
        //Given
        List<RentalBooks> rentalBooksList;
        Reader reader = new Reader("name", "surname");
        readerDao.save(reader);
        Title title1 = new Title("Title1", "Author1", 2001);
        Title title2 = new Title("Title2", "Author2", 2002);
        CopiesOfBooks copy = new CopiesOfBooks("available");
        CopiesOfBooks copy1 = new CopiesOfBooks("available");
        copy.setTitle(title1);
        title1.getCopiesOfBooksList().add(copy);
        copy1.setTitle(title2);
        title2.getCopiesOfBooksList().add(copy1);
        RentalBooks rentalBooks = new RentalBooks(copy, reader);
        RentalBooks rentalBooks1 = new RentalBooks(copy1, reader);
        reader.getRentalBooksList().add(rentalBooks);
        reader.getRentalBooksList().add(rentalBooks1);

        //When
        titleDao.save(title1);
        titleDao.save(title2);
        copyDao.save(copy);
        copyDao.save(copy1);
        rentalDao.save(rentalBooks);
        rentalDao.save(rentalBooks1);

        rentalBooksList = rentalDao.findAll();
        long readerId = reader.getId();
        long title1Id = title1.getId();
        long title2Id = title2.getId();
        long copyId = copy.getId();
        long copy1Id = copy1.getId();
        long rentalId = rentalBooks.getId();
        long rental1Id = rentalBooks1.getId();

        //Then
        assertEquals(2, rentalBooksList.size());

        //Clean up
        rentalDao.delete(rentalId);
        rentalDao.delete(rental1Id);
        copyDao.delete(copyId);
        copyDao.delete(copy1Id);
        titleDao.delete(title1Id);
        titleDao.delete(title2Id);
        readerDao.delete(readerId);
    }

    @Test
    public void shouldFindById(){
        //Given
        List<RentalBooks> rentalBooksList;
        Reader reader = new Reader("name", "surname");
        readerDao.save(reader);
        Title title1 = new Title("Title1", "Author1", 2001);
        Title title2 = new Title("Title2", "Author2", 2002);
        CopiesOfBooks copy = new CopiesOfBooks("available");
        CopiesOfBooks copy1 = new CopiesOfBooks("available");
        copy.setTitle(title1);
        title1.getCopiesOfBooksList().add(copy);
        copy1.setTitle(title2);
        title2.getCopiesOfBooksList().add(copy1);
        RentalBooks rentalBooks = new RentalBooks(copy, reader);
        RentalBooks rentalBooks1 = new RentalBooks(copy1, reader);
        reader.getRentalBooksList().add(rentalBooks);
        reader.getRentalBooksList().add(rentalBooks1);

        //When
        titleDao.save(title1);
        titleDao.save(title2);
        copyDao.save(copy);
        copyDao.save(copy1);
        rentalDao.save(rentalBooks);
        rentalDao.save(rentalBooks1);

        rentalBooksList = rentalDao.findAll();
        long readerId = reader.getId();
        long title1Id = title1.getId();
        long title2Id = title2.getId();
        long copyId = copy.getId();
        long copy1Id = copy1.getId();
        long rentalId = rentalBooks.getId();
        long rental1Id = rentalBooks1.getId();

        //Then
        assertEquals(2, rentalBooksList.size());
        assertEquals("Title1", rentalDao.findById(rentalId).get().getCopiesOfBooks().getTitle().getTitle());
        assertEquals("Title2", rentalDao.findById(rental1Id).get().getCopiesOfBooks().getTitle().getTitle());
        assertEquals("Author1", rentalDao.findById(rentalId).get().getCopiesOfBooks().getTitle().getAuthor());
        assertEquals("Author2", rentalDao.findById(rental1Id).get().getCopiesOfBooks().getTitle().getAuthor());

        //Clean up
        rentalDao.delete(rentalId);
        rentalDao.delete(rental1Id);
        copyDao.delete(copyId);
        copyDao.delete(copy1Id);
        titleDao.delete(title1Id);
        titleDao.delete(title2Id);
        readerDao.delete(readerId);
    }

    @Test
    public void shouldDelete(){
        //Given
        Reader reader = new Reader("name", "surname");
        readerDao.save(reader);
        Title title1 = new Title("Title1", "Author1", 2001);
        Title title2 = new Title("Title2", "Author2", 2002);
        CopiesOfBooks copy = new CopiesOfBooks("available");
        CopiesOfBooks copy1 = new CopiesOfBooks("available");
        copy.setTitle(title1);
        title1.getCopiesOfBooksList().add(copy);
        copy1.setTitle(title2);
        title2.getCopiesOfBooksList().add(copy1);
        RentalBooks rentalBooks = new RentalBooks(copy, reader);
        RentalBooks rentalBooks1 = new RentalBooks(copy1, reader);
        reader.getRentalBooksList().add(rentalBooks);
        reader.getRentalBooksList().add(rentalBooks1);

        //When
        titleDao.save(title1);
        titleDao.save(title2);
        copyDao.save(copy);
        copyDao.save(copy1);
        rentalDao.save(rentalBooks);
        rentalDao.save(rentalBooks1);

        long readerId = reader.getId();
        long title1Id = title1.getId();
        long title2Id = title2.getId();
        long copyId = copy.getId();
        long copy1Id = copy1.getId();
        long rentalId = rentalBooks.getId();
        long rental1Id = rentalBooks1.getId();

        //Then
        assertEquals(2, rentalDao.findAll().size());
        rentalDao.delete(rental1Id);
        assertEquals(1, rentalDao.findAll().size());
        rentalDao.delete(rentalId);
        assertTrue(rentalDao.findAll().size() == 0);

        //Clean up
        copyDao.delete(copyId);
        copyDao.delete(copy1Id);
        titleDao.delete(title1Id);
        titleDao.delete(title2Id);
        readerDao.delete(readerId);
    }
}
