package com.kodillalibrary.kodillalibrary.mappers;

import com.kodillalibrary.kodillalibrary.domain.copiesOfBooks.CopiesOfBooks;
import com.kodillalibrary.kodillalibrary.domain.copiesOfBooks.CopiesOfBooksDto;
import com.kodillalibrary.kodillalibrary.service.DbServiceTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CopyMapper {
    @Autowired
    private DbServiceTitle dbServiceTitle;

    public  CopiesOfBooks mapToCopiesOfBook(final CopiesOfBooksDto copiesOfBooksDto){
        CopiesOfBooks copiesOfBooks = new CopiesOfBooks(copiesOfBooksDto.getStatus());
        copiesOfBooks.setId(copiesOfBooksDto.getId());
        copiesOfBooks.setTitle(dbServiceTitle.getBookById(copiesOfBooksDto.getTitleId()));
        return copiesOfBooks;
    }

    public CopiesOfBooksDto mapToCopiesOfBookDto(final CopiesOfBooks copiesOfBooks){
        CopiesOfBooksDto copiesOfBooksDto = new CopiesOfBooksDto (
                copiesOfBooks.getId(),
                copiesOfBooks.getStatus(),
                copiesOfBooks.getTitle().getId());
        return copiesOfBooksDto;
    }

    public List<CopiesOfBooksDto> mapToCopiesOfBookDtoList(final List<CopiesOfBooks> copiesOfBooksList){
        return copiesOfBooksList.stream()
                .map(c -> new CopiesOfBooksDto(
                        c.getId(),
                        c.getStatus(),
                        c.getTitle().getId()))
                .collect(Collectors.toList());
    }
}
