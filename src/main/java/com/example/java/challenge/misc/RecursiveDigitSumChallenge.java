package com.example.java.challenge.misc;

import lombok.extern.slf4j.Slf4j;

/**
 * Given an integer, we need to find the super digit of the integer.
 * - If number has only 1 digit, then its super digit is number
 * - Otherwise, the super digit of number is equal to the super digit of the sum of the digits of number
 * - The number is created by concatenating the string n times. .
 * For example:
 * Case 1: "148", 3 -> 148148148 -> 3
 * Case 2: "9875", 4 -> 9875987598759875 -> 8
 * Case 3: "123", 3 -> 123123123 -> 9
 */
@Slf4j
public class RecursiveDigitSumChallenge {

    /**
     * Calculates super digit as sum of digits from given String number
     * IMPORTANT: initial number is set as original number concatenated N times
     *
     * @param number {@link String} original number
     * @param times  int quantity of times that original number should be concatenated
     * @return super digit as sum of digits
     */
    public static int superDigit(final String number, final int times) {
        long superDigit = superDigitNumber(number.toCharArray()) * times;

        while (superDigit > 9) {
            superDigit = superDigitNumber(String.valueOf(superDigit).toCharArray());
        }

        return (int) superDigit;
    }

    /**
     * Calculates super digit as sum of digits from given char array number
     *
     * @param number char array number
     * @return super digit as sum of digits
     */
    private static long superDigitNumber(final char[] number) {
        int value = 0;

        for (int i = 0; i < number.length; i++) {
            value += Character.getNumericValue(number[i]);
        }

        return value;
    }
}
