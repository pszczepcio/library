package com.kodillalibrary.kodillalibrary;

import com.kodillalibrary.kodillalibrary.domain.rentBooks.RentBooks;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface RentBooksDao extends CrudRepository<RentBooks, Long> {
    @Override
    List<RentBooks> findAll();

    @Override
    RentBooks save (RentBooks rentBooks);

    @Override
    void delete(RentBooks entity);

    @Override
    void delete(Long rentBookId);

    Optional<RentBooks> findById(Long id);
}
