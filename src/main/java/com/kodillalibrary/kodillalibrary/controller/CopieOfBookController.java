package com.kodillalibrary.kodillalibrary.controller;

import com.kodillalibrary.kodillalibrary.domain.copiesOfBooks.CopiesOfBooksDto;
import com.kodillalibrary.kodillalibrary.exception.CopiesOfBookNotFoundException;
import com.kodillalibrary.kodillalibrary.mappers.CopyMapper;
import com.kodillalibrary.kodillalibrary.service.DbServiceCopyBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/library")
public class CopieOfBookController {

    @Autowired
    private DbServiceCopyBook serviceCopyBook;

    @Autowired
    private CopyMapper copyMapper;

    @RequestMapping(method = RequestMethod.POST, value = "createCopy")
    public void createCopy(@RequestBody CopiesOfBooksDto copiesOfBooksDto){
        serviceCopyBook.save(copyMapper.mapToCopiesOfBook(copiesOfBooksDto));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getCopies")
    public List<CopiesOfBooksDto> copiesOfBooksDtoList(){
        return copyMapper.mapToCopiesOfBookDtoList(serviceCopyBook.getAllCopies());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getCopyById")
    public CopiesOfBooksDto getCopyById(@RequestParam final Long copyId) throws CopiesOfBookNotFoundException {
        return copyMapper.mapToCopiesOfBookDto(serviceCopyBook.getCopyById(copyId).orElseThrow(CopiesOfBookNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteById")
    public void deleteCopyById(@RequestParam final long copyId){
        serviceCopyBook.deleteById(copyId);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "updateCopy")
    public CopiesOfBooksDto updateCopy(@RequestBody CopiesOfBooksDto copiesOfBooksDto){
        return copyMapper.mapToCopiesOfBookDto(serviceCopyBook.save(copyMapper.mapToCopiesOfBook(copiesOfBooksDto)));
    }
}
