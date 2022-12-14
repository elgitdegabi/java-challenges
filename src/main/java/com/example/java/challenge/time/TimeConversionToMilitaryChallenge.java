package com.example.java.challenge.time;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Given a time in -hour AM/PM format, convert it to military (24-hour) time.
 * For example:
 * Case 1: 12:01:00PM ->  12:01:00
 * Case 2: 12:01:00AM ->  00:01:00
 */
@Slf4j
public class TimeConversionToMilitaryChallenge {

    /**
     * Converts current time from AM/PM format to military format
     *
     * @param currentTime {@link String}
     */
    public static String convert(final String currentTime) {
        LocalTime localTime = LocalTime.parse(currentTime, DateTimeFormatter.ofPattern("hh:mm:ssa"));
        return localTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
}
