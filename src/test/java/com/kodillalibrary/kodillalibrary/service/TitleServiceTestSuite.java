package com.kodillalibrary.kodillalibrary.service;

import com.kodillalibrary.kodillalibrary.domain.title.Title;
import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@RequiredArgsConstructor
public class TitleServiceTestSuite {

    @Autowired
    private TitleService titleService;

    @Test
    public void shouldSaveBook(){
        //Given
        Title title = new Title("title", "author", 1999);

        //When
        titleService.saveBook(title);
        long titleId = title.getId();

        //Then
        assertEquals("title", titleService.getBookById(titleId).getTitle());
        assertEquals("author", titleService.getBookById(titleId).getAuthor());
        assertEquals(1999, titleService.getBookById(titleId).getYearOfPublishment());

        //Clean up
        titleService.deleteBookById(titleId);
    }

    @Test
    public void shouldGetAllBooks(){
        //Given
        List<Title> titleList;
        Title title1 = new Title("title1", "author1", 1999);
        Title title2 = new Title("title2", "author2", 2000);
        Title title3 = new Title("title3", "author3", 2001);

        //When
        titleService.saveBook(title1);
        titleService.saveBook(title2);
        titleService.saveBook(title3);
        titleList = titleService.getAllBooks();

        long id1 = title1.getId();
        long id2 = title2.getId();
        long id3 = title3.getId();

        //Then
        assertEquals(3, titleList.size());

        //Clean up
        titleService.deleteBookById(id3);
        titleService.deleteBookById(id2);
        titleService.deleteBookById(id1);
    }

    @Test
    public void shouldGetBookById(){
        //Given
        Title title1 = new Title("title1", "author1", 1999);
        Title title2 = new Title("title2", "author2", 2000);
        Title title3 = new Title("title3", "author3", 2001);

        //When
        titleService.saveBook(title1);
        titleService.saveBook(title2);
        titleService.saveBook(title3);

        long id1 = title1.getId();
        long id2 = title2.getId();
        long id3 = title3.getId();

        //Then
        assertEquals("title1", titleService.getBookById(id1).getTitle());
        assertEquals("title2", titleService.getBookById(id2).getTitle());
        assertEquals("title3", titleService.getBookById(id3).getTitle());
        assertEquals("author1", titleService.getBookById(id1).getAuthor());
        assertEquals("author2", titleService.getBookById(id2).getAuthor());
        assertEquals("author3", titleService.getBookById(id3).getAuthor());

        //Clean up
        titleService.deleteBookById(id3);
        titleService.deleteBookById(id2);
        titleService.deleteBookById(id1);
    }

    @Test
    public void shouldDeleteBookByTitle(){
        //Given
        Title title1 = new Title("title1", "author1", 1999);
        Title title2 = new Title("title2", "author2", 2000);
        Title title3 = new Title("title3", "author3", 2001);

        //When
        titleService.saveBook(title1);
        titleService.saveBook(title2);
        titleService.saveBook(title3);

        //Then
        assertEquals(3, titleService.getAllBooks().size());
        titleService.deleteBookByTitle("title3");
        titleService.deleteBookByTitle("title2");
        titleService.deleteBookByTitle("title1");
        assertEquals(0, titleService.getAllBooks().size());
    }

    @Test
    public void shouldDeleteBookById(){
        //Given
        Title title1 = new Title("title1", "author1", 1999);
        Title title2 = new Title("title2", "author2", 2000);
        Title title3 = new Title("title3", "author3", 2001);

        //When
        titleService.saveBook(title1);
        titleService.saveBook(title2);
        titleService.saveBook(title3);

        long id1 = title1.getId();
        long id2 = title2.getId();
        long id3 = title3.getId();

        //Then
        assertEquals(3, titleService.getAllBooks().size());
        titleService.deleteBookById(id1);
        titleService.deleteBookById(id2);
        titleService.deleteBookById(id3);
        assertEquals(0, titleService.getAllBooks().size());
    }

}
