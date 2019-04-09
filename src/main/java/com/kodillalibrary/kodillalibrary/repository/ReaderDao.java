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
public interface ReaderDao extends CrudRepository<Reader, Integer> {
    @Override
    List<Reader> findAll();

    @Override
    Reader save (Reader reader);

    @Override
    Reader findOne(Integer id);

    @Override
    void delete(Integer integer);

    Reader findByNameAndSurname(String name, String surname);

    void deleteByNameAndSurname(String name, String surname);
}