package com.example.java.challenge.string;

import lombok.extern.slf4j.Slf4j;

/**
 * Your task is to sort a given string. Each word in the string will contain a single number.
 * This number is the position the word should have in the result.
 * Note: Numbers can be from 1 to 9. So 1 will be the first word (not 0). If the input string is empty,
 * return an empty string. The words in the input String will only contain valid consecutive numbers.
 * For example:
 * Case 1: "is2 Thi1s T4est 3a" -> "Thi1s is2 3a T4est"
 * Case 2: "4of Fo1r pe6ople g3ood th5e the2" -> "Fo1r the2 g3ood 4of th5e pe6ople"
 * Case 3: "" -> ""
 */
@Slf4j
public class OrderStringByCharNumberChallenge {

    /**
     * Orders given {@link String} words based on digits letters
     *
     * @param words {@link String}
     * @return {@link String} in order
     */
    public static String order(final String words) {
        String[] wordArray = words.split(" ");
        StringBuffer result = new StringBuffer("");

        for (int i = 0; i < 10; i++) { // only 1..9 digits are allowed
            for (String word : wordArray) {
                if (word.contains(String.valueOf(i))) {
                    result.append(word).append(" ");
                }
            }
        }

        return result.toString().trim();
    }
}
