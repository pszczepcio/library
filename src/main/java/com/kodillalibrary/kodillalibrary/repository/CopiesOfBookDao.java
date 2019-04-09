package com.kodillalibrary.kodillalibrary.repository;

import com.kodillalibrary.kodillalibrary.domain.copiesOfBooks.CopiesOfBooks;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface CopiesOfBookDao extends CrudRepository<CopiesOfBooks, Integer> {
    @Override
    List<CopiesOfBooks> findAll();

    @Override
    CopiesOfBooks save(CopiesOfBooks title);

    CopiesOfBooks findById(Integer id);

    @Override
    void delete(Integer id);

    @Override
    void delete(CopiesOfBooks copiesOfBooks);

    @Override
    void deleteAll();
}
