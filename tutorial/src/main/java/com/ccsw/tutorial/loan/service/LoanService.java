package com.ccsw.tutorial.loan.service;

import com.ccsw.tutorial.game.model.Game;
import com.ccsw.tutorial.loan.dto.LoanDto;
import com.ccsw.tutorial.loan.dto.LoanSearchDto;
import com.ccsw.tutorial.loan.model.Loan;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface LoanService {

    /**
     * Recupera los juegos filtrando opcionalmente por título y/o categoría
     *
     * @param gameTitle título del juego
     * @param idClient  PK del cliente
     * @param date      fecha del prestamo que queremos
     * @return {@link List} de {@link Game}
     */
    List<Loan> find(String gameTitle, Long idClient, LocalDate date);


    /**
     * Recupera un {@link Loan} a través de su ID
     *
     * @param id PK de la entidad
     * @return {@link Loan}
     */
    Loan get(Long id);

    /**
     * Método para recuperar un listado paginado de {@link Loan}
     *
     * @param dto dto de búsqueda
     * @return {@link Page} de {@link Loan}
     */
    Page<Loan> findPage(LoanSearchDto dto);


    /**
     * Método para crear o actualizar un {@link Loan}
     *
     * @param id  PK de la entidad
     * @param dto datos de la entidad
     */
    void save(Long id, LoanDto dto) throws Exception;


    /**
     * Método para crear o actualizar un {@link Loan}
     *
     * @param id PK de la entidad
     */
    void delete(Long id) throws Exception;


    /**
     * Recupera un listado de {@link Loan}
     *
     * @return {@link List} de {@link Loan}
     */
    List<Loan> findAll();
}
