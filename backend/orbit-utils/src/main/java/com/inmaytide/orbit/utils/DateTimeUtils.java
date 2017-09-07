package com.inmaytide.orbit.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public class DateTimeUtils {

    /**
     * @param hour
     * @param minute
     * @param second
     * @return
     */
    public static String format(String date, int hour, int minute, int second, String pattern) {
        TemporalAccessor localDate = DateTimeFormatter.ofPattern("yyyy-M-d").parse(date);
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.from(localDate), LocalTime.of(hour, minute, second));
        return DateTimeFormatter.ofPattern(pattern).format(localDateTime);
    }

}
