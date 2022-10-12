package com.example.java.challenge.string;

import lombok.extern.slf4j.Slf4j;

/**
 * Personal and free version based on an interview question: "given a word, return true if its a palindrome, otherwise return false".
 * For example:
 * Case 1: "radar" -> true
 * Case 2: "raddar" -> true
 * Case 3: "Radar" -> false
 */
@Slf4j
public class PalindromeWordChallenge {

    /**
     * Verifies if given {@link String} text is a palindrome
     *
     * @param text {@link String}
     * @return boolean value
     */
    public static boolean isPalindrome(final String text) {
        char[] charArray = text.toCharArray();

        for (int i = 0; i < text.length() / 2; i++) {
            if (charArray[i] != charArray[text.length() - 1 - i]) {
                return false;
            }
        }

        return true;
    }
}
