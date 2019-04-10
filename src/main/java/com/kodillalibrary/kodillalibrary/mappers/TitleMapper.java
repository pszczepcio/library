package com.kodillalibrary.kodillalibrary.mappers;

import com.kodillalibrary.kodillalibrary.domain.title.Title;
import com.kodillalibrary.kodillalibrary.domain.title.TitleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class TitleMapper {

    @Autowired
    private CopyMapper copyMapper;

    public Title mapToTitle(final TitleDto titleDto){
        return new Title(
                titleDto.getTitle(),
                titleDto.getAuthor(),
                titleDto.getYearOfPublishment());
    }

    public Title mapFromDataBaseToTitle(final TitleDto titleDto){
        Title title = new Title(
                titleDto.getTitle(),
                titleDto.getAuthor(),
                titleDto.getYearOfPublishment()
        );
        title.setId(titleDto.getId());
        title.setCopiesOfBooksList(titleDto.getCopiesOfBooksList());
        return title;
    }

    public TitleDto mapToTitleDto(final Title title){
        TitleDto titleDto = new TitleDto(
                title.getTitle(),
                title.getAuthor(),
                title.getYearOfPublishment());
        titleDto.setId(title.getId());
        titleDto.setCopiesOfBooksList(title.getCopiesOfBooksList());
        return titleDto;
    }

    ////
    public TitleDto mapToTitleDtoAllParam(final Title title){
       return new TitleDto(
                title.getId(),
                title.getTitle(),
                title.getAuthor(),
                title.getYearOfPublishment(),
                title.getCopiesOfBooksList());
    }
    ////
    public List<TitleDto> mapToTitleDtoList(final List<Title> titleList){
        return titleList.stream()
                .map(t -> new TitleDto(
                        t.getId(),
                        t.getTitle(),
                        t.getAuthor(),
                        t.getYearOfPublishment(),
                        t.getCopiesOfBooksList()))
                .collect(Collectors.toList());
    }
}
