package com.kodillalibrary.kodillalibrary.controller;

import com.kodillalibrary.kodillalibrary.domain.copiesOfBooks.CopiesOfBooksDto;
import com.kodillalibrary.kodillalibrary.domain.reader.ReaderDto;
import com.kodillalibrary.kodillalibrary.domain.title.TitleDto;
import com.kodillalibrary.kodillalibrary.mappers.CopyMapper;
import com.kodillalibrary.kodillalibrary.mappers.ReaderMapper;
import com.kodillalibrary.kodillalibrary.mappers.TitleMapper;
import com.kodillalibrary.kodillalibrary.service.DbServiceCopyBook;
import com.kodillalibrary.kodillalibrary.service.DbServiceReader;
import com.kodillalibrary.kodillalibrary.service.DbServiceTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/library")
public class LibraryController {

    @Autowired
    private DbServiceReader service;

    @Autowired
    private DbServiceTitle dbServiceTitle;

    @Autowired
    private DbServiceCopyBook serviceCopyBook;

    @Autowired
    private ReaderMapper readerMapper;

    @Autowired
    private TitleMapper titleMapper;

    @Autowired
    private CopyMapper copyMapper;

//      Request for Reader

    @RequestMapping(method = RequestMethod.GET, value = "getReaders")
    public List<ReaderDto> getAllReaders(){
        return readerMapper.mapToReaderDtoList(service.getAllReader());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getReader")
    public ReaderDto getReader(@RequestParam String name, String surname){
        return readerMapper.mapToReaderDto(service.findReaderByNameAndSurname(name, surname));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getReaderById")
    public ReaderDto getReaderById(@RequestParam Integer readerId){
        return readerMapper.mapToReaderDto(service.getReader(readerId));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createReader", consumes = APPLICATION_JSON_VALUE)
    public void createReader(@RequestBody ReaderDto readerDto){
        service.saveReaders(readerMapper.mapToReader(readerDto));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteReaderByNameAndSurname")
    public void deleteReaderByNameAndSurname(@RequestParam String name, String surname){
        service.deleteReaderByNameAndSurname(name, surname);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteReaderById")
    public void deleteReaderById(@RequestParam Integer readerId){
        service.deleteReaderById(readerId);
    }


//      Request for title

    @RequestMapping(method = RequestMethod.GET, value = "getTitles")
    public List<TitleDto> getAllTitles(){
        return titleMapper.mapToTitleDtoList(dbServiceTitle.getAllBooks());
    }

    @RequestMapping(method = RequestMethod.GET, value =  "getTitleById")
    public TitleDto getTitleById(@RequestParam Integer titleId){
        return titleMapper.mapToTitleDto(dbServiceTitle.getBookById(titleId));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createBookTitle")
    public void createBookTitle(@RequestBody TitleDto titleDto){

        dbServiceTitle.saveBook(titleMapper.mapToTitle(titleDto));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteBookByTitle")
    public void deleteBookByTitle(@RequestParam String title){
        dbServiceTitle.deleteBookByTitle(title);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteBookById")
    public void deleteBookById(@RequestParam Integer titleId){
        dbServiceTitle.deleteBookById(titleId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateBook")
    public TitleDto updateBook(@RequestBody TitleDto titleDto){
        return titleMapper.mapToTitleDto(dbServiceTitle.saveBook(titleMapper.mapFromDataBaseToTitle(titleDto)));
    }

//    Request for Copies od Book

    @RequestMapping(method = RequestMethod.POST, value = "createCopy")
    public void createCopy(@RequestBody CopiesOfBooksDto copiesOfBooksDto){
        serviceCopyBook.createCopies(copyMapper.mapToCopiesOfBooks(copiesOfBooksDto));
    }

//    @RequestMapping(method = RequestMethod.GET, value = "getAllCopies")
//    public List<CopiesOfBooksDto> getAllCopies(){
//       return copyMapper.mapToCopiesOfBookDtoList(serviceCopyBook.getAllCopies());
//    }

//    @RequestMapping(method = RequestMethod.GET, value = "getCopyById")
//    public CopiesOfBooksDto getCopyById(@RequestParam Integer copyId){
//        return copyMapper.mapToCopiesOfBookDto(serviceCopyBook.getCopyById(copyId));
//    }
//
//    @RequestMapping(method = RequestMethod.DELETE, value = "deleteCopyById")
//    public void deleteCopyById(@RequestParam Integer copyId){
//        serviceCopyBook.deleteById(copyId);
//    }
//
//    @RequestMapping(method = RequestMethod.PUT, value = "updateCopy")
//    public CopiesOfBooksDto updateCopy(@RequestBody CopiesOfBooksDto copiesOfBooksDto){
//        return copyMapper.mapToCopiesOfBookDto(serviceCopyBook.createCopies(copyMapper.mapFromDataBaseToCopiesOfBooks(copiesOfBooksDto)));
//    }


}
