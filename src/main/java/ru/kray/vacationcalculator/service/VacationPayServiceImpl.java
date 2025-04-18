package ru.kray.vacationcalculator.service;

import org.springframework.stereotype.Service;
import ru.kray.vacationcalculator.domain.VacationPay;
import ru.kray.vacationcalculator.dto.VacationPayRequest;
import ru.kray.vacationcalculator.dto.VacationPayResponse;
import ru.kray.vacationcalculator.util.HolidayChecker;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class VacationPayServiceImpl implements VacationPayService {
    private static final double AVERAGE_DAYS_IN_MONTH = 29.3;
    private final HolidayChecker holidayChecker;

    public VacationPayServiceImpl(HolidayChecker holidayChecker) {
        this.holidayChecker = holidayChecker;
    }

    @Override
    public VacationPayResponse calculateVacationPay(VacationPayRequest request) {
        long averageMonthlySalary = request.getAverageMonthlySalary();
        int vacationDays = request.getVacationDays();
        Optional<LocalDate> startDate = Optional.ofNullable(request.getStartDate());
        Optional<LocalDate> endDate = Optional.ofNullable(request.getEndDate());

        VacationPay vacationPay = calculate(averageMonthlySalary, vacationDays, startDate, endDate);
        return new VacationPayResponse(vacationPay.getAmount());
    }

    private VacationPay calculate(long averageMonthlySalary, int vacationDays,
                                  Optional<LocalDate> startDate, Optional<LocalDate> endDate) {
        double dailySalary = averageMonthlySalary / AVERAGE_DAYS_IN_MONTH;
        long vacationPayAmount;

        if (startDate.isPresent() && endDate.isPresent()) {
            long workingDays = holidayChecker.countWorkingDays(startDate.get(), endDate.get());
            vacationPayAmount = Math.round(dailySalary * workingDays);
        } else {
            vacationPayAmount = Math.round(dailySalary * vacationDays);
        }

        return new VacationPay(vacationPayAmount);
    }
}