package com.app.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.sql.Timestamp.valueOf;

public class DateConverterUtil {

    private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";

    private static DateTimeFormatter getFormatter() {
        return DateTimeFormatter.ofPattern(PATTERN);
    }

    public static Timestamp convertStringDateToTimestamp(String incomeDate){
        DateTimeFormatter formatter = getFormatter();
        LocalDateTime parsedDate = LocalDateTime.parse(incomeDate, formatter);
        return valueOf(parsedDate);
    }

    public static String convertTimestampDateToString(Timestamp incomeDate){
        DateTimeFormatter formatter = getFormatter();
        LocalDateTime dateTime = incomeDate.toLocalDateTime();
        return dateTime.format(formatter);
    }
}
