package com.kodillalibrary.kodillalibrary.controller;

import com.kodillalibrary.kodillalibrary.domain.title.TitleDto;
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
public class TitleControllerTestSuite {

    @Autowired
    private TitleController titleController;

    @Test
    public void shouldCreateBookTitle(){
        //Given
        TitleDto titleDto = new TitleDto("title", "author", 2000);

        //When
        titleController.createBookTitle(titleDto);
        List<TitleDto> titleDtoList = titleController.getAllTitles();

        //Then
        assertEquals("title", titleController.getTitleById(titleDtoList.get(0).getId()).getTitle());
        assertEquals("author", titleController.getTitleById(titleDtoList.get(0).getId()).getAuthor());
        assertEquals(2000, titleController.getTitleById(titleDtoList.get(0).getId()).getYearOfPublishment());

        //Clean up
        titleController.deleteBookById(titleDtoList.get(0).getId());
    }

    @Test
    public void shouldgetAllTitles(){
        //Given
        TitleDto titleDto1 = new TitleDto("title1", "author1", 2001);
        TitleDto titleDto2 = new TitleDto("title2", "author2", 2002);
        TitleDto titleDto3 = new TitleDto("title3", "author3", 2003);

        //When
        titleController.createBookTitle(titleDto1);
        titleController.createBookTitle(titleDto2);
        titleController.createBookTitle(titleDto3);
        List<TitleDto> titleDtoList = titleController.getAllTitles();

        //Then
        assertEquals(3, titleDtoList.size());

        //Clean up
        titleController.deleteBookById(titleDtoList.get(2).getId());
        titleController.deleteBookById(titleDtoList.get(1).getId());
        titleController.deleteBookById(titleDtoList.get(0).getId());
    }

    @Test
    public void shouldGetTitleById(){
        //Given
        TitleDto titleDto1 = new TitleDto("title1", "author1", 2001);
        TitleDto titleDto2 = new TitleDto("title2", "author2", 2002);
        TitleDto titleDto3 = new TitleDto("title3", "author3", 2003);

        //When
        titleController.createBookTitle(titleDto1);
        titleController.createBookTitle(titleDto2);
        titleController.createBookTitle(titleDto3);
        List<TitleDto> titleDtoList = titleController.getAllTitles();

        //then
        assertEquals("title2", titleController.getTitleById(titleDtoList.get(1).getId()).getTitle());
        assertEquals("author2", titleController.getTitleById(titleDtoList.get(1).getId()).getAuthor());
        assertEquals(2002, titleController.getTitleById(titleDtoList.get(1).getId()).getYearOfPublishment());

        //Clean up
        titleController.deleteBookById(titleDtoList.get(2).getId());
        titleController.deleteBookById(titleDtoList.get(1).getId());
        titleController.deleteBookById(titleDtoList.get(0).getId());
    }

    @Test
    public void shouldDeleteBookByTitle(){
        //Given
        TitleDto titleDto1 = new TitleDto("title1", "author1", 2001);
        TitleDto titleDto2 = new TitleDto("title2", "author2", 2002);
        TitleDto titleDto3 = new TitleDto("title3", "author3", 2003);

        //When
        titleController.createBookTitle(titleDto1);
        titleController.createBookTitle(titleDto2);
        titleController.createBookTitle(titleDto3);

        //then
        assertEquals(3, titleController.getAllTitles().size());
        titleController.deleteBookByTitle("title3");
        assertEquals(2, titleController.getAllTitles().size());
        titleController.deleteBookByTitle("title2");
        assertEquals(1, titleController.getAllTitles().size());
        titleController.deleteBookByTitle("title1");
        assertEquals(0, titleController.getAllTitles().size());
    }

    @Test
    public void shouldDeleteBookById(){
        //Given
        TitleDto titleDto1 = new TitleDto("title1", "author1", 2001);
        TitleDto titleDto2 = new TitleDto("title2", "author2", 2002);
        TitleDto titleDto3 = new TitleDto("title3", "author3", 2003);

        //When
        titleController.createBookTitle(titleDto1);
        titleController.createBookTitle(titleDto2);
        titleController.createBookTitle(titleDto3);
        List<TitleDto> titleDtoList = titleController.getAllTitles();

        //then
        assertEquals(3, titleController.getAllTitles().size());
        titleController.deleteBookById(titleDtoList.get(2).getId());
        assertEquals(2, titleController.getAllTitles().size());
        titleController.deleteBookById(titleDtoList.get(1).getId());
        assertEquals(1, titleController.getAllTitles().size());
        titleController.deleteBookById(titleDtoList.get(0).getId());
        assertEquals(0, titleController.getAllTitles().size());
    }

    @Test
    public void shouldUpdateBook(){
        //Given
        TitleDto title1 = new TitleDto("title1", "author1", 2001);
        titleController.createBookTitle(title1);

        //When
        List<TitleDto> titleDtos = titleController.getAllTitles();
        long id = titleDtos.get(0).getId();
        TitleDto updateTitle1 = new TitleDto(
                id,
                "title2",
                "author2",
                2002
        );
        titleController.updateBook(updateTitle1);

        //Then
        assertTrue(titleController.getAllTitles().size() == 1);
        assertEquals(1, titleController.getAllTitles().get(0).getId());
        assertEquals("title2", titleController.getAllTitles().get(0).getTitle());
        assertEquals("author2", titleController.getAllTitles().get(0).getAuthor());
        assertEquals(2002, titleController.getAllTitles().get(0).getYearOfPublishment());

        //When
        titleController.deleteBookById(id);
    }
}
