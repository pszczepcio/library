package com.kodillalibrary.kodillalibrary.controller;

import com.kodillalibrary.kodillalibrary.domain.title.TitleDto;
import com.kodillalibrary.kodillalibrary.mappers.TitleMapper;
import com.kodillalibrary.kodillalibrary.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/library")
public class TitleController {

    @Autowired
    private TitleService titleService;

    @Autowired
    private TitleMapper titleMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getTitles")
    public List<TitleDto> getAllTitles(){
        return titleMapper.mapToTitleDtoList(titleService.getAllBooks());
    }

    @RequestMapping(method = RequestMethod.GET, value =  "getTitleById")
    public TitleDto getTitleById(@RequestParam Long titleId){
        return titleMapper.mapToTitleDto(titleService.getBookById(titleId));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createBookTitle")
    public void createBookTitle(@RequestBody TitleDto titleDto){
        titleService.saveBook(titleMapper.mapToTitle(titleDto));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteBookByTitle")
    public void deleteBookByTitle(@RequestParam String title){
        titleService.deleteBookByTitle(title);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteBookById")
    public void deleteBookById(@RequestParam Long titleId){
        titleService.deleteBookById(titleId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateBook")
    public TitleDto updateBook(@RequestBody TitleDto titleDto){
        return titleMapper.mapToTitleDto(titleService.saveBook(titleMapper.mapFromDataBaseToTitle(titleDto)));
    }
}
