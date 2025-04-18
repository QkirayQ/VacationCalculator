package ru.kray.vacationcalculator.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@PropertySource("classpath:holidays.properties")
public class HolidayChecker {
    private final Set<LocalDate> holidays;

    public HolidayChecker(@Value("${holidays}") String holidaysString) {
        this.holidays = Arrays.stream(holidaysString.split(","))
                .map(String::trim)
                .map(LocalDate::parse)
                .collect(Collectors.toSet());
    }

    public long countWorkingDays(LocalDate startDate, LocalDate endDate) {
        long workingDays = 0;
        LocalDate currentDate = startDate;

        while (currentDate.isBefore(endDate)) {
            if (isWorkingDay(currentDate)) {
                workingDays++;
            }
            currentDate = currentDate.plusDays(1);
        }

        return workingDays;
    }

    private boolean isWorkingDay(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return !holidays.contains(date) &&
                dayOfWeek != DayOfWeek.SATURDAY &&
                dayOfWeek != DayOfWeek.SUNDAY;
    }
}