package com.example.java.challenge.string;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * Personal and free version based on Google's interview question: "given a book, find the longest word for each page".
 * Initial assumption:
 * - each page should be process separately
 * Extra info:
 * - interesting article about different approach and implementations: https://pythonspeed.com/articles/data-doesnt-fit-in-memory/
 * - test using pages from Picture of Dorian Gray: https://www.gutenberg.org/files/174/174-h/174-h.htm
 * For example:
 * Case 1: "Some text text" -> the largest word is "Some"
 */
@Slf4j
public class BookLargestWordChallenge {

    /**
     * Gets the largest word from given page
     *
     * @param pageNumber  int page number
     * @param pageContent {@link String} page content
     * @return {@link Map} with page number and the largest word
     */
    public static Map<Integer, String> largestWord(final int pageNumber, final String pageContent) {
        if (pageNumber > 0 && pageContent != null && pageContent.length() > 0) {
            Map largestWord = new HashMap();
            largestWord.put(pageNumber, calculate(pageContent));

            return largestWord;
        }

        return Collections.EMPTY_MAP;
    }

    /**
     * Calculates the largest word from given page content
     *
     * @param pageContent {@link String} page content
     * @return {@link String} the largest word
     */
    private static String calculate(final String pageContent) {
        String[] words = pageContent.split("[ ,.\n\r]");
        return Arrays.stream(words).max(Comparator.comparingInt(w -> w.length())).get();
    }
}
