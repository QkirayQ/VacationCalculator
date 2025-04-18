package ru.kray.vacationcalculator.dto;

public class VacationPayResponse {
    private final long vacationPay;

    public VacationPayResponse(long vacationPay) {
        this.vacationPay = vacationPay;
    }

    public long getVacationPay() {
        return vacationPay;
    }
}