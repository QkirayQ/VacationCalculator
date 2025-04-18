package ru.kray.vacationcalculator.service;

import ru.kray.vacationcalculator.dto.VacationPayRequest;
import ru.kray.vacationcalculator.dto.VacationPayResponse;

public interface VacationPayService {
    VacationPayResponse calculateVacationPay(VacationPayRequest request);
}