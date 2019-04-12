package com.kodillalibrary.kodillalibrary.controller;

import com.kodillalibrary.kodillalibrary.domain.title.TitleDto;
import com.kodillalibrary.kodillalibrary.mappers.TitleMapper;
import com.kodillalibrary.kodillalibrary.service.DbServiceTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/library")
public class TitleController {

    @Autowired
    private DbServiceTitle dbServiceTitle;

    @Autowired
    private TitleMapper titleMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getTitles")
    public List<TitleDto> getAllTitles(){
        return titleMapper.mapToTitleDtoList(dbServiceTitle.getAllBooks());
    }

    @RequestMapping(method = RequestMethod.GET, value =  "getTitleById")
    public TitleDto getTitleById(@RequestParam Long titleId){
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
    public void deleteBookById(@RequestParam Long titleId){
        dbServiceTitle.deleteBookById(titleId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateBook")
    public TitleDto updateBook(@RequestBody TitleDto titleDto){
        return titleMapper.mapToTitleDto(dbServiceTitle.saveBook(titleMapper.mapFromDataBaseToTitle(titleDto)));
    }
}
