package com.example.java.challenge.array;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * A bookseller has lots of books classified in 26 categories labeled A, B, ... Z.
 * Each book has a code c of 3, 4, 5 or more characters. The 1st character of a code is a capital letter which defines the book category.
 * In the bookseller's stocklist each code c is followed by a space and by a positive integer n (int n >= 0) which indicates
 * the quantity of books of this code in stock.
 * Find all the books of L with codes belonging to each category of M and to sum their quantity according to each category.
 * If L or M are empty return string is "".
 * For example:
 * Case 1: {"ABAR 200", "CDXE 500", "BKWR 250", "BTSQ 890"} &  {"A", "B"} -> "(A : 200) - (B : 1140)")
 */
@Slf4j
public class BookSellerChallenge {

    /**
     * Summarizes stock by category based on given arrays of articles and categories
     *
     * @param lstOfArt       {@link String[]}
     * @param lstOf1stLetter {@link String[]}
     * @return {@link String} with summarization
     */
    public static String stockSummary(final String[] lstOfArt, final String[] lstOf1stLetter) {
        if (lstOfArt.length > 0 && lstOf1stLetter.length > 0) {
            Map<Character, Long> countMap = new HashMap<>();
            StringBuffer stringBuffer = new StringBuffer();

            for (int i = 0; i < lstOfArt.length; i++) {
                Character key = lstOfArt[i].charAt(0);
                Long quantity = Long.valueOf(lstOfArt[i].substring(lstOfArt[i].indexOf(" ") + 1));

                countMap.put(key, countMap.getOrDefault(key, 0L) + quantity);
            }

            for (int i = 0; i < lstOf1stLetter.length; i++) {
                Character key = lstOf1stLetter[i].charAt(0);
                stringBuffer.append("(").append(key).append(" : ").append(countMap.getOrDefault(key, 0L)).append(")");

                if (i < lstOf1stLetter.length - 1) {
                    stringBuffer.append(" - ");
                }
            }

            return stringBuffer.toString();
        }

        return "";
    }
}
