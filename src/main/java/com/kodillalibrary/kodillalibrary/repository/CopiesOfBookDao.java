package com.kodillalibrary.kodillalibrary.repository;

import com.kodillalibrary.kodillalibrary.domain.copiesOfBooks.CopiesOfBooks;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface CopiesOfBookDao extends CrudRepository<CopiesOfBooks, Long> {
    @Override
    List<CopiesOfBooks> findAll();

    @Override
    CopiesOfBooks save(CopiesOfBooks copiesOfBooks);

    Optional<CopiesOfBooks> findById(Long id);

    @Override
    void delete(Long id);

    @Override
    void delete(CopiesOfBooks copiesOfBooks);

    @Override
    void deleteAll();
}
