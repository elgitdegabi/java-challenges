package com.example.java.challenge.string;

import lombok.extern.slf4j.Slf4j;

/**
 * Return the number (count) of vowels in the given string.
 * We will consider a, e, i, o, u as vowels for this Kata (but not y).
 * The input string will only consist of lower case letters and/or spaces.
 * For example:
 * Case 1: abracadabra -> 5
 */
@Slf4j
public class StringVowelCountChallenge {

    /**
     * Counts vowel from given {@link String}
     *
     * @param str {@link String}
     * @return vowel count
     */
    public static int getCount(final String str) {
        return (int) str.chars().filter(c -> "aeiou".indexOf(c) >= 0).count();
    }
}
