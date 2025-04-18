package ru.kray.vacationcalculator.domain;

public class VacationPay {
    private final long amount;

    public VacationPay(long amount) {
        this.amount = amount;
    }

    public long getAmount() {
        return amount;
    }
}