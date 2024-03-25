package com.gabeWebTest.webTest.utils;

import java.time.LocalDate;
import java.time.Period;

public class AgeCalculator {

    private final static LocalDate BIRTHDAY= LocalDate.of(2000,03,27);

    public static int CALCULATE_AGE() {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(BIRTHDAY, currentDate);
        return period.getYears();
    }
}
