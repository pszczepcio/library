package com.kodillalibrary.kodillalibrary.repository;

import com.kodillalibrary.kodillalibrary.domain.rentBooks.RentBooks;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface RentBooksDao extends CrudRepository<RentBooks, Integer> {
    @Override
    List<RentBooks> findAll();

    @Override
    RentBooks save (RentBooks rentBooks);

    @Override
    void delete(RentBooks entity);

    @Override
    RentBooks findOne(Integer id);
}
