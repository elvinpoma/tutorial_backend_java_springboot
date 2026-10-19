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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;


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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
