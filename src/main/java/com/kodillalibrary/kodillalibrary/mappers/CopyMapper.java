package com.kodillalibrary.kodillalibrary.mappers;

import com.kodillalibrary.kodillalibrary.domain.copiesOfBooks.CopiesOfBooks;
import com.kodillalibrary.kodillalibrary.domain.copiesOfBooks.CopiesOfBooksDto;
import com.kodillalibrary.kodillalibrary.service.DbServiceTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CopyMapper {
    @Autowired
    private TitleMapper titleMapper;
    @Autowired
    DbServiceTitle dbServiceTitle;

    public CopiesOfBooks mapToCopiesOfBooks(final CopiesOfBooksDto copiesOfBooksDto){
       return new CopiesOfBooks(
               copiesOfBooksDto.getId(),
               copiesOfBooksDto.getStatus(),
               titleMapper.mapToTitle(copiesOfBooksDto.getTitleDto()),
               new ArrayList<>());
    }

//    public CopiesOfBooks mapFromDataBaseToCopiesOfBooks(final CopiesOfBooksDto copiesOfBooksDto){
//        CopiesOfBooks copiesOfBooks = new CopiesOfBooks(
//                copiesOfBooksDto.getStatus()
//
//        );
//        copiesOfBooks.setTitle(copiesOfBooksDto.getTitle());
//        copiesOfBooks.setId(copiesOfBooksDto.getId());
//        copiesOfBooks.setRentBooksList(copiesOfBooksDto.getRentBooksList());
//        return copiesOfBooks;
//    }
//
//    public CopiesOfBooksDto mapToCopiesOfBookDto(final CopiesOfBooks copiesOfBooks){
//        return new CopiesOfBooksDto(
//                copiesOfBooks.getId(),
//                copiesOfBooks.getStatus(),
//                copiesOfBooks.getTitle(),
//                copiesOfBooks.getRentBooksList()
//        );
//    }

//    public List<CopiesOfBooksDto> mapToCopiesOfBookDtoList(final List<CopiesOfBooks> copiesOfBooksList){
//        return copiesOfBooksList.stream()
//                .map(c -> new CopiesOfBooksDto(
//                        c.getId(),
//                        c.getStatus(),
//                        c.getTitle().getId()))
//                .collect(Collectors.toList());
//    }
}
