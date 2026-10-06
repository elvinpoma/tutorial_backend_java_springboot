package com.ccsw.tutorial.loan.dto;

import com.ccsw.tutorial.client.dto.ClientDto;
import com.ccsw.tutorial.game.dto.GameDto;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

/**
 * @author elportil
 */
public class LoanDto {
    private Long id;
    private GameDto game;
    private ClientDto client;


    //Usaremos el json formater de jackson para pasar fechas, con este formato
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate start_date;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate end_date;


    //Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GameDto getGame() {
        return game;
    }

    public void setGame(GameDto game) {
        this.game = game;
    }

    public ClientDto getClient() {
        return client;
    }

    public void setClient(ClientDto client) {
        this.client = client;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }
}
