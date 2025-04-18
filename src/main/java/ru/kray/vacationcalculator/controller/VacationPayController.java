package ru.kray.vacationcalculator.controller;

import jakarta.validation.constraints.Min;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.kray.vacationcalculator.dto.VacationPayRequest;
import ru.kray.vacationcalculator.dto.VacationPayResponse;
import ru.kray.vacationcalculator.service.VacationPayService;

import java.time.LocalDate;

@RestController
public class VacationPayController {
    private final VacationPayService vacationPayService;

    public VacationPayController(VacationPayService vacationPayService) {
        this.vacationPayService = vacationPayService;
    }

    @GetMapping("/calculate")
    public ResponseEntity<VacationPayResponse> calculateVacationPay(
            @RequestParam @Min(0) long averageMonthlySalary,
            @RequestParam @Min(1) int vacationDays,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        VacationPayRequest request = new VacationPayRequest();
        request.setAverageMonthlySalary(averageMonthlySalary);
        request.setVacationDays(vacationDays);
        request.setStartDate(startDate);
        request.setEndDate(endDate);
        VacationPayResponse response = vacationPayService.calculateVacationPay(request);
        return ResponseEntity.ok(response);
    }
}