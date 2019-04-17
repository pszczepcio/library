package com.kodillalibrary.kodillalibrary.repository;

import com.kodillalibrary.kodillalibrary.domain.title.Title;
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
public class TitleRepositoryTestSuite {

    @Autowired
    private TitleDao titleDao;

    @Test
    public void shouldSave(){
        //Given
        Title title1 = new Title("Title1", "Author1" , 2000);

        //When
        titleDao.save(title1);
        long titleId = title1.getId();

        //Then
        assertEquals("Title1", titleDao.findById(titleId).getTitle());
        assertEquals("Author1", titleDao.findById(titleId).getAuthor());
        assertEquals(2000, titleDao.findById(titleId).getYearOfPublishment());

        //Clean up
        titleDao.delete(titleId);
    }

    @Test
    public void shouldGetAllBooks(){
        //Given
        List<Title> titleList;
        Title title1 = new Title("Title1", "Author1" , 2000);
        Title title2 = new Title("Title2", "Author2" , 2002);
        Title title3 = new Title("Title3", "Author3" , 2003);

        //When
        titleDao.save(title1);
        titleDao.save(title2);
        titleDao.save(title3);
        titleList = titleDao.findAll();
        long title1Id = title1.getId();
        long title2Id = title2.getId();
        long title3Id = title3.getId();

        //Then
        assertEquals(3, titleList.size());
        assertEquals("Title1", titleDao.findById(title1Id).getTitle());
        assertEquals("Author2", titleDao.findById(title2Id).getAuthor());
        assertEquals(2003, titleDao.findById(title3Id).getYearOfPublishment());

        //Clean up
        titleDao.delete(title3Id);
        titleDao.delete(title2Id);
        titleDao.delete(title1Id);
    }

    @Test
    public void shouldFindById(){
        //Given
        Title title1 = new Title("Title1", "Author1" , 2000);
        Title title2 = new Title("Title2", "Author2" , 2002);
        Title title3 = new Title("Title3", "Author3" , 2003);

        //When
        titleDao.save(title1);
        titleDao.save(title2);
        titleDao.save(title3);
        long title1Id = title1.getId();
        long title2Id = title2.getId();
        long title3Id = title3.getId();

        //Then
        assertEquals("Title2", titleDao.findById(title2Id).getTitle());
        assertEquals("Author2", titleDao.findById(title2Id).getAuthor());
        assertEquals(2002, titleDao.findById(title2Id).getYearOfPublishment());

        //Clean up
        titleDao.delete(title3Id);
        titleDao.delete(title2Id);
        titleDao.delete(title1Id);
    }

    @Test
    public void shouldDeleteByTitle(){
        //Given
        List<Title> titleList;
        Title title1 = new Title("Title1", "Author1" , 2000);
        Title title2 = new Title("Title2", "Author2" , 2002);
        Title title3 = new Title("Title3", "Author3" , 2003);

        //When
        titleDao.save(title1);
        titleDao.save(title2);
        titleDao.save(title3);
        titleList = titleDao.findAll();
        long title1Id = title1.getId();
        long title2Id = title2.getId();
        long title3Id = title3.getId();

        //Then
       assertEquals(3, titleList.size());
       titleDao.deleteByTitle("Title3");
       titleList = titleDao.findAll();
       assertEquals(2, titleList.size());
        //Clean up
        titleDao.delete(title2Id);
        titleDao.delete(title1Id);
    }
}
