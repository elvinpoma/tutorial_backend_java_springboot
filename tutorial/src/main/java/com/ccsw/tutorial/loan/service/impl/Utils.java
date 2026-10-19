package com.ccsw.tutorial.loan.service.impl;

import com.ccsw.tutorial.loan.model.Loan;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Utils {

    public static void validation14Days(LocalDate start, LocalDate end) throws Exception {
        long days = ChronoUnit.DAYS.between(start, end);
        if (days < 0) {
            throw new Exception("End date cannot be before start date");
        }

        if (days > 14) {
            throw new Exception("Loan period cannot exceed 14 days");
        }
    }

    public static void dateValidator(LocalDate start, LocalDate end) throws Exception {
        if (end.isBefore(start)) {
            throw new Exception("The end date cannot be before the start date");
        }
    }

    public static void gameListValidator(List<Loan> gameList) throws Exception {


        for (Loan loanFound : gameList) {
            System.out.println("Loan encontrado: " + loanFound.getId());
            }
        if (!gameList.isEmpty()) {
            throw new Exception("The game is already loaned");
        }
    }

    //Comprobamos si el  cliente tiene mas de 2 juegos en un mismo periodo de tiempo.
    public static void clientLoanValidator(List<Loan> clientLoans, LocalDate startDate, LocalDate endDate) throws Exception {
        LocalDate current = startDate;
        while (!current.isAfter(endDate)) {

            int gamesThisDay = 1; // el préstamo nuevo

            for (Loan loan : clientLoans) {

                if (!current.isBefore(loan.getStartDate())
                        && !current.isAfter(loan.getEndDate())) {

                    gamesThisDay++;
                }
            }

            if (gamesThisDay > 2) {
                throw new Exception(
                        "A client cannot have more than 2 games on the same day");
            }

            current = current.plusDays(1);
        }
    }
}
