package com.kodillalibrary.kodillalibrary.controller;

import com.kodillalibrary.kodillalibrary.domain.copiesOfBooks.CopiesOfBooksDto;
import com.kodillalibrary.kodillalibrary.mappers.CopyMapper;
import com.kodillalibrary.kodillalibrary.service.DbServiceCopyBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/library")
public class CopieOfBookController {
    @Autowired
    private DbServiceCopyBook serviceCopyBook;

    @Autowired
    private CopyMapper copyMapper;

    @RequestMapping(method = RequestMethod.POST, value = "createCopy1")
    public void createCopy1(@RequestBody CopiesOfBooksDto copiesOfBooksDto){
        serviceCopyBook.save(copyMapper.mapToCopiesOfBook1(copiesOfBooksDto));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createCopy/{id}", consumes = APPLICATION_JSON_VALUE)
    public void createCopy(@RequestBody CopiesOfBooksDto copiesOfBooksDto,
                           @PathVariable("id") Long id){
        serviceCopyBook.createCopies(copyMapper.mapToCopiesOfBooks(copiesOfBooksDto, id), id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getCopies")
    public List<CopiesOfBooksDto> copiesOfBooksDtoList(){
        return copyMapper.mapToCopiesOfBookDtoList(serviceCopyBook.getAllCopies());
    }
}
