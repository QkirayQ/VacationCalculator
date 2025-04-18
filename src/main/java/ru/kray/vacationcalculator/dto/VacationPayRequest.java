package ru.kray.vacationcalculator.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class VacationPayRequest {
    @NotNull
    @Min(0)
    private Long averageMonthlySalary;

    @NotNull
    @Min(1)
    private Integer vacationDays;

    private LocalDate startDate;
    private LocalDate endDate;

    public Long getAverageMonthlySalary() {
        return averageMonthlySalary;
    }

    public void setAverageMonthlySalary(Long averageMonthlySalary) {
        this.averageMonthlySalary = averageMonthlySalary;
    }

    public Integer getVacationDays() {
        return vacationDays;
    }

    public void setVacationDays(Integer vacationDays) {
        this.vacationDays = vacationDays;
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