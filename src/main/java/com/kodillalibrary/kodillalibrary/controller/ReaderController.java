package com.kodillalibrary.kodillalibrary.controller;

import com.kodillalibrary.kodillalibrary.domain.reader.ReaderDto;
import com.kodillalibrary.kodillalibrary.exception.ReaderNotFoundException;
import com.kodillalibrary.kodillalibrary.mappers.ReaderMapper;
import com.kodillalibrary.kodillalibrary.service.DbServiceReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/library")
public class ReaderController {
    @Autowired
    private DbServiceReader service;

    @Autowired
    private ReaderMapper readerMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getReaders")
    public List<ReaderDto> getAllReaders(){
        return readerMapper.mapToReaderDtoList(service.getAllReader());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getReader")
    public ReaderDto getReader(@RequestParam String name, String surname) throws ReaderNotFoundException{
        return readerMapper.mapToReaderDto(service.findReaderByNameAndSurname(name, surname).orElseThrow(ReaderNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getReaderById")
    public ReaderDto getReaderById(@RequestParam Long readerId) throws ReaderNotFoundException{
        return readerMapper.mapToReaderDto(service.getReader(readerId).orElseThrow(ReaderNotFoundException::new));
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
    public void deleteReaderById(@RequestParam Long readerId){
        service.deleteReaderById(readerId);
    }
}
