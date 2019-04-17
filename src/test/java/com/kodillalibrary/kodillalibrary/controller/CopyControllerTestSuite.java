package com.kodillalibrary.kodillalibrary.controller;

import com.kodillalibrary.kodillalibrary.domain.copiesOfBooks.CopiesOfBooksDto;
import com.kodillalibrary.kodillalibrary.domain.title.TitleDto;
import com.kodillalibrary.kodillalibrary.exception.CopiesOfBookNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CopyControllerTestSuite {

    @Autowired
    private CopyController copyController;

    @Autowired
    private TitleController titleController;

    @Test
    public void shouldCreateCopy(){
        //Given
        TitleDto titleDto = new TitleDto("title", "author", 2000);
        titleController.createBookTitle(titleDto);
        long titleDtoId = titleController.getAllTitles().get(0).getId();

        //When
        CopiesOfBooksDto copy = new CopiesOfBooksDto("available", titleDtoId);
        copyController.createCopy(copy);
        long copyId = copyController.getCopiesOfBooksDtoList().get(0).getId();

        //Then
        assertEquals("title", titleController.getTitleById(copyController.getCopiesOfBooksDtoList().get(0).getTitleId()).getTitle());
        assertEquals("author", titleController.getTitleById(copyController.getCopiesOfBooksDtoList().get(0).getTitleId()).getAuthor());
        assertEquals(2000, titleController.getTitleById(copyController.getCopiesOfBooksDtoList().get(0).getTitleId()).getYearOfPublishment());
        assertEquals("available", copyController.getCopiesOfBooksDtoList().get(0).getStatus());

        //Clean up
        copyController.deleteCopyById(copyId);
        titleController.deleteBookById(titleDtoId);
    }

    @Test
    public void shouldGetCopiesOfBooksDtoList(){
        //Given
        TitleDto titleDto = new TitleDto("title", "author", 2000);
        titleController.createBookTitle(titleDto);
        long titleDtoId = titleController.getAllTitles().get(0).getId();

        //When
        CopiesOfBooksDto copy1 = new CopiesOfBooksDto("available", titleDtoId);
        CopiesOfBooksDto copy2 = new CopiesOfBooksDto("not available", titleDtoId);
        copyController.createCopy(copy1);
        copyController.createCopy(copy2);
        long copy1Id = copyController.getCopiesOfBooksDtoList().get(0).getId();
        long copy2Id = copyController.getCopiesOfBooksDtoList().get(1).getId();

        //Then
        assertEquals(2, copyController.getCopiesOfBooksDtoList().size());

        //Clean up
        copyController.deleteCopyById(copy2Id);
        copyController.deleteCopyById(copy1Id);
        titleController.deleteBookById(titleDtoId);
    }

    @Test
    public void shouldGetCopyById() throws CopiesOfBookNotFoundException {
        //Given
        TitleDto titleDto = new TitleDto("title", "author", 2000);
        titleController.createBookTitle(titleDto);
        long titleDtoId = titleController.getAllTitles().get(0).getId();

        //When
        CopiesOfBooksDto copy1 = new CopiesOfBooksDto("available", titleDtoId);
        CopiesOfBooksDto copy2 = new CopiesOfBooksDto("not available", titleDtoId);
        copyController.createCopy(copy1);
        copyController.createCopy(copy2);
        long copy1Id = copyController.getCopiesOfBooksDtoList().get(0).getId();
        long copy2Id = copyController.getCopiesOfBooksDtoList().get(1).getId();

        //Then
        assertEquals("available", copyController.getCopyById(copy1Id).getStatus());
        assertEquals("not available", copyController.getCopyById(copy2Id).getStatus());

        //Clean up
        copyController.deleteCopyById(copy2Id);
        copyController.deleteCopyById(copy1Id);
        titleController.deleteBookById(titleDtoId);
    }

    @Test
    public void shouldUpdateCopy(){
        TitleDto titleDto = new TitleDto("title", "author", 2000);
        titleController.createBookTitle(titleDto);
        long titleDtoId = titleController.getAllTitles().get(0).getId();

        //When
        CopiesOfBooksDto copy1 = new CopiesOfBooksDto("available", titleDtoId);
        CopiesOfBooksDto copy2 = new CopiesOfBooksDto("not available", titleDtoId);
        copyController.createCopy(copy1);
        copyController.createCopy(copy2);
        long copy1Id = copyController.getCopiesOfBooksDtoList().get(0).getId();
        long copy2Id = copyController.getCopiesOfBooksDtoList().get(1).getId();

        CopiesOfBooksDto updateCopy1 = new CopiesOfBooksDto(copy1Id, "not available", titleDtoId);
        copyController.updateCopy(updateCopy1);

        //Then
        assertTrue(2 == copyController.getCopiesOfBooksDtoList().size());
        assertEquals("not available", copyController.getCopiesOfBooksDtoList().get(0).getStatus() );

        //Clean up
        copyController.deleteCopyById(copy2Id);
        copyController.deleteCopyById(copy1Id);
        titleController.deleteBookById(titleDtoId);
    }
}
