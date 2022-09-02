package com.example.java.challenge.misc;

import lombok.extern.slf4j.Slf4j;

/**
 * Given a string of lowercase letters determine the index of a character that can be removed to make the string a palindrome.
 * For example:
 * Case 1: aaab -> 3
 * Case 2: baa -> 0
 * Case 3: aaa -> -1
 * Case 4: hgygsvlfwcwnswtuhmyaljkqlqjjqlqkjlaymhutwsnwcflvsgygh -> 8
 * Case 5: hgygsvlfcwnswtuhmyaljkqlqjjqlqkjlaymhutwsnwcwflvsgygh -> 44
 */
@Slf4j
public class PalindromeIndexChallenge {

    /**
     * Calculates letter index to be removed from given {@link String} text to create a palindrome
     *
     * @param s {@link String} text
     * @return int index to be removed
     */
    public static int calculate(final String s) {
        String text = s.toLowerCase();

        for (int i = 0, j = text.length() - 1; i < j; i++, j--) {
            if (text.charAt(i) != text.charAt(j)) {
                return (calculate(text.substring(i + 1, j + 1)) == -1 ? i : j);
            }
        }

        return -1;
    }
}
