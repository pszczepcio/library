package com.kodillalibrary.kodillalibrary.repository;

import com.kodillalibrary.kodillalibrary.domain.reader.Reader;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

//
@Repository
@Transactional
public interface ReaderDao extends CrudRepository<Reader, Long> {
    @Override
    List<Reader> findAll();

    @Override
    Reader save (Reader reader);

    Optional<Reader> findById(Long id);

    @Override
    void delete(Long id);

    Optional<Reader> findByNameAndSurname(String name, String surname);

    void deleteByNameAndSurname(String name, String surname);
}