package com.ccsw.tutorial.loan.repository;

import com.ccsw.tutorial.loan.model.Loan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface LoanRepository extends CrudRepository<Loan, Long>, JpaSpecificationExecutor<Loan> {

    @Override
    @EntityGraph(attributePaths = {"game", "client"})
    List<Loan> findAll(Specification<Loan> spec);


    List<Loan> findByGameIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            Long gameId,
            LocalDate endDate,
            LocalDate startDate
    );

    List<Loan> findByClientIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            Long clientId,
            LocalDate endDate,
            LocalDate startDate
    );

    Page<Loan> findAll(Pageable pageable);


    List<Loan> findByStartDateLessThanEqualAndEndDateGreaterThanEqual(
            LocalDate date1,
            LocalDate date2
    );

}

