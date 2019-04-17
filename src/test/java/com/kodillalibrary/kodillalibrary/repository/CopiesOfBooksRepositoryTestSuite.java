package com.kodillalibrary.kodillalibrary.repository;

import com.kodillalibrary.kodillalibrary.domain.copiesOfBooks.CopiesOfBooks;
import com.kodillalibrary.kodillalibrary.domain.title.Title;
import com.kodillalibrary.kodillalibrary.repository.copy.dao.CopyDao;
import com.kodillalibrary.kodillalibrary.repository.title.dao.TitleDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CopiesOfBooksRepositoryTestSuite {

    @Autowired
    private TitleDao titleDao;

    @Autowired
    private CopyDao copyDao;

    @Test
    public void shoudlSave(){
        //Given
        Title title1 = new Title("Title1", "Author1" , 2000);
        CopiesOfBooks copy1 = new CopiesOfBooks("available");
        CopiesOfBooks copy2 = new CopiesOfBooks("not available");

        //When
        copy1.setTitle(title1);
        copy2.setTitle(title1);
        title1.getCopiesOfBooksList().add(copy1);
        title1.getCopiesOfBooksList().add(copy2);
        titleDao.save(title1);
        copyDao.save(copy1);
        copyDao.save(copy2);
        long title1Id = title1.getId();
        long copy1Id = copy1.getId();
        long copy2Id = copy2.getId();

        //Then
        assertEquals("available", copyDao.findById(copy1Id).get().getStatus());
        assertEquals("not available", copyDao.findById(copy2Id).get().getStatus());

        //Clean up
        copyDao.delete(copy2Id);
        copyDao.delete(copy1Id);
        titleDao.delete(title1Id);
    }

    @Test
    public void shouldFindAll(){
        //Given
        List<CopiesOfBooks> copiesList;
        Title title1 = new Title("Title1", "Author1" , 2000);
        CopiesOfBooks copy1 = new CopiesOfBooks("available");
        CopiesOfBooks copy2 = new CopiesOfBooks("not available");

        //When
        copy1.setTitle(title1);
        copy2.setTitle(title1);
        title1.getCopiesOfBooksList().add(copy1);
        title1.getCopiesOfBooksList().add(copy2);
        titleDao.save(title1);
        copyDao.save(copy1);
        copyDao.save(copy2);
        copiesList = copyDao.findAll();
        long title1Id = title1.getId();
        long copy1Id = copy1.getId();
        long copy2Id = copy2.getId();

        //Then
        assertEquals(2, copyDao.findAll().size());

        //Clean up
        copyDao.delete(copy2Id);
        copyDao.delete(copy1Id);
        titleDao.delete(title1Id);
    }

    @Test
    public void shouldFindById(){
        //Given
        Title title1 = new Title("Title1", "Author1" , 2000);
        CopiesOfBooks copy1 = new CopiesOfBooks("available");
        CopiesOfBooks copy2 = new CopiesOfBooks("not available");

        //When
        copy1.setTitle(title1);
        copy2.setTitle(title1);
        title1.getCopiesOfBooksList().add(copy1);
        title1.getCopiesOfBooksList().add(copy2);
        titleDao.save(title1);
        copyDao.save(copy1);
        copyDao.save(copy2);

        long title1Id = title1.getId();
        long copy1Id = copy1.getId();
        long copy2Id = copy2.getId();

        //Then
        assertEquals("available", copyDao.findById(copy1Id).get().getStatus());
        assertEquals("not available", copyDao.findById(copy2Id).get().getStatus());
        assertEquals("Title1", copyDao.findById(copy1Id).get().getTitle().getTitle());
        assertEquals("Author1", copyDao.findById(copy2Id).get().getTitle().getAuthor());

        //When
        copyDao.delete(copy2Id);
        copyDao.delete(copy1Id);
        titleDao.delete(title1Id);
    }

    @Test
    public void shouldDelete(){
        //Given
        Title title1 = new Title("Title1", "Author1" , 2000);
        CopiesOfBooks copy1 = new CopiesOfBooks("available");
        CopiesOfBooks copy2 = new CopiesOfBooks("not available");

        //When
        copy1.setTitle(title1);
        copy2.setTitle(title1);
        title1.getCopiesOfBooksList().add(copy1);
        title1.getCopiesOfBooksList().add(copy2);
        titleDao.save(title1);
        copyDao.save(copy1);
        copyDao.save(copy2);
        long title1Id = title1.getId();
        long copy1Id = copy1.getId();
        long copy2Id = copy2.getId();

        //Then
        assertEquals(2, copyDao.findAll().size());
        copyDao.delete(copy2Id);
        assertEquals(1, copyDao.findAll().size());
        copyDao.delete(copy1Id);
        assertEquals(0, copyDao.findAll().size());

        //Clean up
        titleDao.delete(title1Id);
    }
}
