package com.kodillalibrary.kodillalibrary.repository;

import com.kodillalibrary.kodillalibrary.domain.title.Title;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface TitleDao extends CrudRepository<Title, Long> {
    @Override
    List<Title> findAll();

    @Override
    Title save(Title title);

    Title findById(Long id);

    @Override
    void delete(Long id);

    @Override
    void deleteAll();

    void deleteByTitle(String title);


}
