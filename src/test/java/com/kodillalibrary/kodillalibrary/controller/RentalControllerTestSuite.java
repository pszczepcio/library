package com.kodillalibrary.kodillalibrary.controller;

import com.kodillalibrary.kodillalibrary.domain.booksRental.RentalBooksDto;
import com.kodillalibrary.kodillalibrary.domain.copiesOfBooks.CopiesOfBooksDto;
import com.kodillalibrary.kodillalibrary.domain.reader.ReaderDto;
import com.kodillalibrary.kodillalibrary.domain.title.TitleDto;
import com.kodillalibrary.kodillalibrary.exception.CopiesOfBookNotFoundException;
import com.kodillalibrary.kodillalibrary.exception.ReaderNotFoundException;
import com.kodillalibrary.kodillalibrary.exception.RentBooksNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RentalControllerTestSuite {

    @Autowired
    private ReaderController readerController;

    @Autowired
    private TitleController titleController;

    @Autowired
    private CopyController copyController;

    @Autowired
    private RentalController rentalController;

    @Test
    public void shouldCreateRentBook() throws ReaderNotFoundException, CopiesOfBookNotFoundException, RentBooksNotFoundException {
        //Given
        ReaderDto readerDto = new ReaderDto("name", "surname");
        readerController.createReader(readerDto);
        long readerDtoId = readerController.getReader("name", "surname").getId();

        TitleDto titleDto = new TitleDto("title", "author", 2000);
        titleController.createBookTitle(titleDto);
        long titleDtoId = titleController.getAllTitles().get(0).getId();

        CopiesOfBooksDto copiesOfBooksDto = new CopiesOfBooksDto("available", titleDtoId);
        copyController.createCopy(copiesOfBooksDto);
        long copiesOfBooksDtoId = copyController.getCopiesOfBooksDtoList().get(0).getId();

        //When
        RentalBooksDto rentalBooksDto = new RentalBooksDto(copiesOfBooksDtoId, readerDtoId);
        rentalController.createRentBook(rentalBooksDto);
        long rentalBooksDtoId = rentalController.rentBooksDtoList().get(0).getId();

        //Then
        assertEquals(1, rentalController.rentBooksDtoList().size());
        assertEquals(readerDtoId, rentalController.rentBooksDtoList().get(0).getReaderId());
        assertEquals(titleDtoId, copyController.getCopyById(rentalController.rentBooksDtoList().get(0).getCopiesOfBooksId()).getTitleId());
        assertEquals(copiesOfBooksDtoId, rentalController.rentBooksDtoList().get(0).getCopiesOfBooksId());
        System.out.println(copyController.getCopyById(rentalController.rentBooksDtoList().get(0).getCopiesOfBooksId()).getStatus());
        assertEquals("title", titleController.getTitleById(copyController.getCopyById(rentalController.rentBooksDtoList().get(0).getCopiesOfBooksId()).getTitleId()).getTitle());
        assertEquals("author", titleController.getTitleById(copyController.getCopyById(rentalController.rentBooksDtoList().get(0).getCopiesOfBooksId()).getTitleId()).getAuthor());
        assertEquals(2000, titleController.getTitleById(copyController.getCopyById(rentalController.rentBooksDtoList().get(0).getCopiesOfBooksId()).getTitleId()).getYearOfPublishment());
        assertEquals("name", readerController.getReaderById(rentalController.rentBooksDtoList().get(0).getReaderId()).getName());
        assertEquals("surname", readerController.getReaderById(rentalController.rentBooksDtoList().get(0).getReaderId()).getSurname());
        assertEquals(readerController.getReaderById(readerDtoId).getDateOfAccountCreation(), readerController.getReaderById(rentalController.rentBooksDtoList().get(0).getReaderId()).getDateOfAccountCreation());

        //When
        rentalController.deleteRentBookById(rentalBooksDtoId);
        copyController.deleteCopyById(copiesOfBooksDtoId);
        titleController.deleteBookById(titleDtoId);
        readerController.deleteReaderById(readerDtoId);
    }

    @Test
    public void shouldRentBooksDtoList() throws ReaderNotFoundException, CopiesOfBookNotFoundException, RentBooksNotFoundException {
        //Given
        ReaderDto readerDto = new ReaderDto("name", "surname");
        readerController.createReader(readerDto);
        long readerDtoId = readerController.getReader("name", "surname").getId();

        TitleDto titleDto = new TitleDto("title", "author", 2000);
        titleController.createBookTitle(titleDto);
        long titleDtoId = titleController.getAllTitles().get(0).getId();

        CopiesOfBooksDto copy1 = new CopiesOfBooksDto("available", titleDtoId);
        copyController.createCopy(copy1);
        long copy1_Id = copyController.getCopiesOfBooksDtoList().get(0).getId();

        CopiesOfBooksDto copy2 = new CopiesOfBooksDto("available", titleDtoId);
        copyController.createCopy(copy2);
        long copy2_Id = copyController.getCopiesOfBooksDtoList().get(1).getId();

        //When
        RentalBooksDto rental_1 = new RentalBooksDto(copy1_Id, readerDtoId);
        rentalController.createRentBook(rental_1);
        long rentalBooksDtoId_1 = rentalController.rentBooksDtoList().get(0).getId();

        RentalBooksDto rental_2 = new RentalBooksDto(copy2_Id, readerDtoId);
        rentalController.createRentBook(rental_2);
        long rentalBooksDtoId_2 = rentalController.rentBooksDtoList().get(1).getId();

        //Then
        assertEquals(2, rentalController.rentBooksDtoList().size());

        //Clean up
        rentalController.deleteRentBookById(rentalBooksDtoId_2);
        rentalController.deleteRentBookById(rentalBooksDtoId_1);
        copyController.deleteCopyById(copy2_Id);
        copyController.deleteCopyById(copy1_Id);
        titleController.deleteBookById(titleDtoId);
        readerController.deleteReaderById(readerDtoId);
    }

    @Test
    public void shouldGetRentBookById() throws ReaderNotFoundException, CopiesOfBookNotFoundException, RentBooksNotFoundException {
        //Given
        ReaderDto readerDto = new ReaderDto("name", "surname");
        readerController.createReader(readerDto);
        long readerDtoId = readerController.getReader("name", "surname").getId();

        TitleDto titleDto1 = new TitleDto("title1", "author1", 2000);
        titleController.createBookTitle(titleDto1);
        long titleDto1_Id = titleController.getAllTitles().get(0).getId();

        TitleDto titleDto2 = new TitleDto("title2", "author2", 2000);
        titleController.createBookTitle(titleDto2);
        long titleDto2_Id = titleController.getAllTitles().get(1).getId();

        CopiesOfBooksDto copy1 = new CopiesOfBooksDto("available", titleDto1_Id);
        copyController.createCopy(copy1);
        long copy1_Id = copyController.getCopiesOfBooksDtoList().get(0).getId();

        CopiesOfBooksDto copy2 = new CopiesOfBooksDto("available", titleDto2_Id);
        copyController.createCopy(copy2);
        long copy2_Id = copyController.getCopiesOfBooksDtoList().get(1).getId();

        //When
        RentalBooksDto rental_1 = new RentalBooksDto(copy1_Id, readerDtoId);
        rentalController.createRentBook(rental_1);
        long rentalBooksDtoId_1 = rentalController.rentBooksDtoList().get(0).getId();

        RentalBooksDto rental_2 = new RentalBooksDto(copy2_Id, readerDtoId);
        rentalController.createRentBook(rental_2);
        long rentalBooksDtoId_2 = rentalController.rentBooksDtoList().get(1).getId();

        //Then
       // assertEquals("available", copyController.getCopyById(rentalController.getRentBookById(rentalBooksDtoId_2).getCopiesOfBooksId()).getStatus());
        assertEquals("title2", titleController.getTitleById(copyController.getCopyById(rentalController.getRentBookById(rentalBooksDtoId_2).getCopiesOfBooksId()).getId()).getTitle());
        assertEquals("author2", titleController.getTitleById(copyController.getCopyById(rentalController.getRentBookById(rentalBooksDtoId_2).getCopiesOfBooksId()).getId()).getAuthor());
        assertEquals(2000, titleController.getTitleById(copyController.getCopyById(rentalController.getRentBookById(rentalBooksDtoId_2).getCopiesOfBooksId()).getId()).getYearOfPublishment());

        //Clean up
        rentalController.deleteRentBookById(rentalBooksDtoId_2);
        rentalController.deleteRentBookById(rentalBooksDtoId_1);
        copyController.deleteCopyById(copy2_Id);
        copyController.deleteCopyById(copy1_Id);
        titleController.deleteBookById(titleDto2_Id);
        titleController.deleteBookById(titleDto1_Id);
        readerController.deleteReaderById(readerDtoId);
    }

    @Test
    public void deleteRentBookById() throws ReaderNotFoundException, CopiesOfBookNotFoundException, RentBooksNotFoundException {
        //Given
        ReaderDto readerDto = new ReaderDto("name", "surname");
        readerController.createReader(readerDto);
        long readerDtoId = readerController.getReader("name", "surname").getId();

        TitleDto titleDto1 = new TitleDto("title1", "author1", 2000);
        titleController.createBookTitle(titleDto1);
        long titleDto1_Id = titleController.getAllTitles().get(0).getId();

        TitleDto titleDto2 = new TitleDto("title2", "author2", 2000);
        titleController.createBookTitle(titleDto2);
        long titleDto2_Id = titleController.getAllTitles().get(1).getId();

        CopiesOfBooksDto copy1 = new CopiesOfBooksDto("available", titleDto1_Id);
        copyController.createCopy(copy1);
        long copy1_Id = copyController.getCopiesOfBooksDtoList().get(0).getId();

        CopiesOfBooksDto copy2 = new CopiesOfBooksDto("available", titleDto2_Id);
        copyController.createCopy(copy2);
        long copy2_Id = copyController.getCopiesOfBooksDtoList().get(1).getId();

        //When
        RentalBooksDto rental_1 = new RentalBooksDto(copy1_Id, readerDtoId);
        rentalController.createRentBook(rental_1);
        long rentalBooksDtoId_1 = rentalController.rentBooksDtoList().get(0).getId();

        RentalBooksDto rental_2 = new RentalBooksDto(copy2_Id, readerDtoId);
        rentalController.createRentBook(rental_2);
        long rentalBooksDtoId_2 = rentalController.rentBooksDtoList().get(1).getId();

        //Then
       assertEquals(2, rentalController.rentBooksDtoList().size());
       rentalController.deleteRentBookById(rentalBooksDtoId_2);
       assertEquals(1, rentalController.rentBooksDtoList().size());
       rentalController.deleteRentBookById(rentalBooksDtoId_1);
       assertEquals(0, rentalController.rentBooksDtoList().size());

        //Clean up
        copyController.deleteCopyById(copy2_Id);
        copyController.deleteCopyById(copy1_Id);
        titleController.deleteBookById(titleDto2_Id);
        titleController.deleteBookById(titleDto1_Id);
        readerController.deleteReaderById(readerDtoId);
    }

    @Test
    public void shouldUpdateRentBook() throws ReaderNotFoundException, CopiesOfBookNotFoundException, RentBooksNotFoundException, ParseException {
        //Given
        ReaderDto readerDto = new ReaderDto("name", "surname");
        readerController.createReader(readerDto);
        long readerDtoId = readerController.getReader("name", "surname").getId();

        TitleDto titleDto1 = new TitleDto("title1", "author1", 2000);
        titleController.createBookTitle(titleDto1);
        long titleDto1_Id = titleController.getAllTitles().get(0).getId();

        TitleDto titleDto2 = new TitleDto("title2", "author2", 2000);
        titleController.createBookTitle(titleDto2);
        long titleDto2_Id = titleController.getAllTitles().get(1).getId();

        CopiesOfBooksDto copy1 = new CopiesOfBooksDto("available", titleDto1_Id);
        copyController.createCopy(copy1);
        long copy1_Id = copyController.getCopiesOfBooksDtoList().get(0).getId();

        CopiesOfBooksDto copy2 = new CopiesOfBooksDto("available", titleDto2_Id);
        copyController.createCopy(copy2);
        long copy2_Id = copyController.getCopiesOfBooksDtoList().get(1).getId();

        RentalBooksDto rentalBooksDto = new RentalBooksDto(copy1_Id, readerDtoId);
        rentalController.createRentBook(rentalBooksDto);
        long rentalBooksDtoId = rentalController.rentBooksDtoList().get(0).getId();
        //When
        RentalBooksDto updateRental = new RentalBooksDto(
                rentalBooksDtoId,
                copy2_Id,
                readerDtoId,
                rentalController.getRentBookById(rentalBooksDtoId).getDateOfRent(),
                "2020-05-11 10:21:16");
        rentalController.updateRental(updateRental);

        //Then
        assertEquals(1, rentalController.rentBooksDtoList().size());
        assertEquals(rentalBooksDtoId, rentalController.rentBooksDtoList().get(0).getId());
        assertTrue(copy1_Id != rentalController.rentBooksDtoList().get(0).getCopiesOfBooksId());
        assertEquals(copy2_Id, rentalController.rentBooksDtoList().get(0).getCopiesOfBooksId());
        assertEquals("2020-05-11 10:21:16", rentalController.rentBooksDtoList().get(0).getDateOfReturn());

        //Clean up
        rentalController.deleteRentBookById(rentalBooksDtoId);
        copyController.deleteCopyById(copy2_Id);
        copyController.deleteCopyById(copy1_Id);
        titleController.deleteBookById(titleDto2_Id);
        titleController.deleteBookById(titleDto1_Id);
        readerController.deleteReaderById(readerDtoId);
    }
}
