package com.ccsw.tutorial.loan.service.impl;

import com.ccsw.tutorial.client.repository.ClientRepository;
import com.ccsw.tutorial.common.criteria.SearchCriteria;
import com.ccsw.tutorial.game.repository.GameRepository;
import com.ccsw.tutorial.loan.dto.LoanDto;
import com.ccsw.tutorial.loan.dto.LoanSearchDto;
import com.ccsw.tutorial.loan.model.Loan;
import com.ccsw.tutorial.loan.repository.LoanRepository;
import com.ccsw.tutorial.loan.service.LoanService;
import com.ccsw.tutorial.loan.specification.LoanSpecification;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.ccsw.tutorial.loan.service.impl.Utils.*;

/**
 * @author elportil
 */

@Service
@Transactional
public class LoanServiceImpl implements LoanService {

    @Autowired
    LoanRepository loanRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    GameRepository gameRepository;

    @Override
    public List<Loan> find(String gameTitle, Long idClient, LocalDate date) {

        LoanSpecification titleSpec = new LoanSpecification(new SearchCriteria("game.title", ":", gameTitle));
        LoanSpecification clientSpec = new LoanSpecification(new SearchCriteria("client.id", ":", idClient));
        //date es una fecha que el usuario da
        //El filtro de debe de encargar de :
        // - Comprobar si hay prestamos en ese dia exacto
        // - Comrpobar si esa fecha esta entre los intervalos de inicio y de final de las demas fechas
        // - No debe de mostrar nada que no cumpla esos parametros anteriores.
        // - Data es algo que envia el usuario
        Specification<Loan> spec =
                titleSpec.and(clientSpec);

        if (date != null) {

            spec = spec.and((root, query, builder) ->
                    builder.and(
                            builder.lessThanOrEqualTo(
                                    root.get("startDate"),
                                    date
                            ),
                            builder.greaterThanOrEqualTo(
                                    root.get("endDate"),
                                    date
                            )
                    )
            );
        }

        return this.loanRepository.findAll(spec);
    }

    /**
     * {@ineritDoc}
     */
    @Override
    public Loan get(Long id) {
        return this.loanRepository.findById(id).orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Loan> findPage(LoanSearchDto dto) {
        return this.loanRepository.findAll(dto.getPageable().getPageable());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Long id, LoanDto dto) throws Exception {


        //Controlador de error para que no se pueda poner la fecha final antes de la fecha inicial
        dateValidator(dto.getStartDate(), dto.getEndDate());

        //Validar si hay 14 dias
        validation14Days(dto.getStartDate(), dto.getEndDate());

        //Validar juego
        //Llamar a la bbdd para ver si el juego esta prestado a alguien
        List<Loan> gameList = this.loanRepository.findByGameIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                dto.getGame().getId(),
                dto.getEndDate(),
                dto.getStartDate()
        );
        //Para pdoder editar un prestamos hay que hacer que el actual no exista porque sino el sistema detecta solapamiento entre prestamos.
        if (id != null) {
            gameList.removeIf(loan -> loan.getId().equals(id));
        }
        gameListValidator(gameList);

        //Validar si un cliente tiene mas de 2 juegos el mismo dia.

        List<Loan> clientLoans = this.loanRepository.findByClientIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                dto.getClient().getId(),
                dto.getEndDate(),
                dto.getStartDate()
        );
        //Para poder editar un prestamos de un cliente que tiene ya 2 prestamos activos, ya que si edito 1 de los 2
        //Para evitar el error de :A client cannot have more than 2 games on the same day
        //Pues le quitamos el actual para poder editarlo de nuevo.
        if (id != null) {
            clientLoans.removeIf(l -> l.getId().equals(id));
        }
        clientLoanValidator(
                clientLoans,
                dto.getStartDate(),
                dto.getEndDate()
        );
        Loan loan;
        if (id == null) {
            loan = new Loan();
        } else {
            loan = this.get(id);
        }
        loan.setClient(
                clientRepository.getById(
                        dto.getClient().getId()
                )
        );

        loan.setGame(
                gameRepository.getById(
                        dto.getGame().getId()
                )
        );
        BeanUtils.copyProperties(dto, loan, "id", "client", "game");

        this.loanRepository.save(loan);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) throws Exception {
        if (this.get(id) == null) {
            throw new Exception("Not exists");
        }

        this.loanRepository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Loan> findAll() {
        return (List<Loan>) this.loanRepository.findAll();
    }
}
