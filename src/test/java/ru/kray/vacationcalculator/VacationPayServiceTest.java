package ru.kray.vacationcalculator;

import org.junit.Test;
import ru.kray.vacationcalculator.dto.VacationPayRequest;
import ru.kray.vacationcalculator.dto.VacationPayResponse;
import ru.kray.vacationcalculator.service.VacationPayService;
import ru.kray.vacationcalculator.service.VacationPayServiceImpl;
import ru.kray.vacationcalculator.util.HolidayChecker;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class VacationPayServiceTest {
    private final VacationPayService vacationPayService;

    public VacationPayServiceTest() {
        HolidayChecker holidayChecker = new HolidayChecker(
                "2025-01-01,2025-01-02,2025-01-03,2025-01-04,2025-01-05,2025-01-06,2025-01-07,2025-01-08," +
                        "2025-02-23,2025-03-08,2025-05-01,2025-05-09,2025-06-12,2025-11-04"
        );
        this.vacationPayService = new VacationPayServiceImpl(holidayChecker);
    }

    @Test
    public void testBasicVacationPayCalculation() {
        VacationPayRequest request = new VacationPayRequest();
        request.setAverageMonthlySalary(30000L);
        request.setVacationDays(14);

        VacationPayResponse response = vacationPayService.calculateVacationPay(request);
        assertEquals(14334L, response.getVacationPay());
    }

    @Test
    public void testVacationPayWithHolidays() {
        VacationPayRequest request = new VacationPayRequest();
        request.setAverageMonthlySalary(30000L);
        request.setVacationDays(14);
        request.setStartDate(LocalDate.of(2025, 1, 1));
        request.setEndDate(LocalDate.of(2025, 1, 14));

        VacationPayResponse response = vacationPayService.calculateVacationPay(request);
        assertEquals(3072L, response.getVacationPay());
    }

    @Test
    public void testVacationPayWithWeekends() {
        VacationPayRequest request = new VacationPayRequest();
        request.setAverageMonthlySalary(30000L);
        request.setVacationDays(7);
        request.setStartDate(LocalDate.of(2025, 4, 7));
        request.setEndDate(LocalDate.of(2025, 4, 13));

        VacationPayResponse response = vacationPayService.calculateVacationPay(request);
        assertEquals(5119L, response.getVacationPay());
    }
}