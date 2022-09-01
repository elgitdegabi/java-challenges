package com.example.java.challenge.misc;

import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Create a RomanNumerals class that can convert a roman numeral to and from an integer value.
 * Modern Roman numerals are written by expressing each digit separately starting with the left most digit and skipping any digit with a value of zero.
 * Input range : 1 <= n < 4000
 *
 * For example:
 * Case 1:  1990 -> 1000=M, 900=CM, 90=XC -> MCMXC
 */
@Slf4j
public class RomanNumberConverterChallenge {

    /**
     * RomanNumberConverter class
     */
    public static class RomanNumberConverter {

        private static  Map<Integer, String> conversionMap = new HashMap<>();

        static {
            conversionMap.put(1000, "M");
            conversionMap.put(900, "CM");
            conversionMap.put(500, "D");
            conversionMap.put(400, "CD");
            conversionMap.put(100, "C");
            conversionMap.put(90, "XC");
            conversionMap.put(50, "L");
            conversionMap.put(40, "XL");
            conversionMap.put(10, "X");
            conversionMap.put(9, "IX");
            conversionMap.put(5, "V");
            conversionMap.put(4, "IV");
            conversionMap.put(1, "I");
        }

        /**
         * Converts to roman number given decimal number
         * @param n int decimal number
         * @return {@link String} roman number
         */
        public static String toRoman(final int n) {
            if (n > 0 && n < 4000) {
                List<Integer> divisorList = conversionMap.keySet().stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
                StringBuffer stringBuffer = new StringBuffer();
                int tempNumber = n;

                while (tempNumber > 0) {
                    for (int i = 0; i < divisorList.size(); i++) {
                        if (tempNumber % divisorList.get(i) < tempNumber) {
                            stringBuffer.append(conversionMap.get(divisorList.get(i)));
                            tempNumber -= divisorList.get(i);
                            break;
                        }
                    }
                }

                log.info("number: {} - roman: {}", n, stringBuffer);

                return stringBuffer.toString();
            }

            return "";
        }

        /**
         * Converts to decimal number the given decimal number
         * @param romanNumeral {@link String} roman number
         * @return int decimal number
         */
        public static int fromRoman(final String romanNumeral) {
            if (romanNumeral.trim().length() > 0) {
                Map<String, Integer> translationMap = conversionMap.entrySet().stream().collect(Collectors.toMap(k -> k.getValue(), k-> k.getKey()));
                int number = 0;

                for (int i = 0; i < romanNumeral.length(); i++) {
                    if (i < romanNumeral.length() - 1 && translationMap.containsKey(romanNumeral.substring(i, i + 2))) {
                        number += translationMap.get(romanNumeral.substring(i, i + 2));
                        i++;
                    } else if  (translationMap.containsKey(romanNumeral.substring(i, i + 1))){
                        number += translationMap.get(romanNumeral.substring(i, i + 1));
                    }
                }

                log.info("roman: {} - number: {}", romanNumeral, number);

                return (number > 0 && number < 4000? number: 0);
            }

            return 0;
        }
    }
}
