package com.example.java.challenge.misc;

import lombok.extern.slf4j.Slf4j;

/**
 * Every book has n pages with page numbers 1 to n. The summary is made by adding up the number of digits of all page numbers.
 * Given the summary, find the number of pages n the book has.
 * Example: If the input is summary=25, then the output must be n=17: The numbers 1 to 17 have 25 digits in total: 1234567891011121314151617.
 * Be aware that you'll get enormous books having up to 100.000 pages.
 * All inputs will be valid..
 * For example:
 * Case 1: 25 -> 17
 */
@Slf4j
public class BookPagesChallenge {

    /**
     * Calculates amount of pages from given summary
     *
     * @param summary int
     * @return int amount of pages
     */
    public static int amountOfPages(final int summary) {
        StringBuffer result = new StringBuffer();
        int page = 0;

        while (result.length() < summary) {
            result.append(++page);
        }

        return page;
    }
}
