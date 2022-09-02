package com.example.java.challenge.string;

import lombok.extern.slf4j.Slf4j;

/**
 * Complete the function that accepts a string parameter, and reverses each word in the string.
 * All spaces in the string should be retained.
 * For example:
 * Case 1: "double  spaces" -> "elbuod  secaps"
 */
@Slf4j
public class ReverseWordsChallenge {

    /**
     * Reverse words from given original text
     *
     * @param original {@link String}
     * @return {@link String}
     */
    public static String reverseWords(final String original) {
        String[] wordArray = original.replace(" ", "| |").split("\\|");
        StringBuffer reverseText = new StringBuffer();

        for (int i = 0; i < wordArray.length; i++) {
            reverseText.append(new StringBuffer(wordArray[i]).reverse());
        }

        return reverseText.toString();
    }
}
