package com.kodillalibrary.kodillalibrary.repository.rentbook.dao;

import com.kodillalibrary.kodillalibrary.domain.booksRental.RentalBooks;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface RentalDao extends CrudRepository<RentalBooks, Long> {
    @Override
    List<RentalBooks> findAll();

    @Override
    RentalBooks save (RentalBooks rentalBooks);

    @Override
    void delete(Long rentBookId);

    Optional<RentalBooks> findById(Long id);
}
