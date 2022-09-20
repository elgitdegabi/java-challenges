package com.example.java.challenge.misc;

import lombok.extern.slf4j.Slf4j;

import java.util.Stack;

/**
 * Given N strings of brackets, determine whether each sequence of brackets is balanced.
 * If a string is balanced, return YES. Otherwise, return NO.
 * For example:
 * Case 1: "{[()]}" -> YES
 * Case 2: "{[(])}" -> NO
 * Case 3: "{{[[(())]]}}" -> YES
 * Case 4: "{{([])}}" -> YES
 * Case 5: "{{)[](}}" -> NO
 * Case 6: "{(([])[])[]}" -> YES
 * Case 7: "{(([])[])[]]}" -> NO
 * Case 8: "{(([])[])[]}[]" -> YES
 */
@Slf4j
public class BalancedBracketsChallenge {

    /**
     * Validates if given String is balanced
     *
     * @param s {@link String}
     * @return {@link String} YES or NO
     */
    public static String isBalanced(final String s) {
        String tmp = s;
        while (tmp.contains("()") || tmp.contains("[]") || tmp.contains("{}")) {
            tmp = tmp.replaceAll("\\(\\)", "")
                    .replaceAll("\\[\\]", "")
                    .replaceAll("\\{\\}", "");
        }
        return (tmp.length() == 0 ? "YES" : "NO");
    }

    /**
     * Validates if given String is balanced
     *
     * @param s {@link String}
     * @return {@link String} YES or NO
     */
    public static String isBalancedObsolete(final String s) {
        if (s.length() < 2 || s.length() % 2 != 0) {
            return "NO";
        }

        char[] text = s.toCharArray();
        Stack<Character> openStack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (isValidOpenChar(text[i])) {
                openStack.push(text[i]);
            }

            if (isValidCloseChar(text[i])
                    && (openStack.size() < 1
                    || openStack.pop().charValue() != getPairCharFor(text[i]))) {
                return "NO";
            }
        }

        return (openStack.size() < 1 ? "YES" : "NO");
    }

    /**
     * Validates if given char is a valid open char
     *
     * @param candidate given char
     * @return true or false
     */
    private static boolean isValidOpenChar(final char candidate) {
        return (candidate == '{' || candidate == '[' || candidate == '(');
    }

    /**
     * Validates if given char is a valid close char
     *
     * @param candidate given char
     * @return true or false
     */
    private static boolean isValidCloseChar(final char candidate) {
        return (candidate == '}' || candidate == ']' || candidate == ')');
    }

    /**
     * Returns valid open char for given close char
     *
     * @param candidate given char
     * @return pair open char or blank
     */
    private static char getPairCharFor(final char candidate) {
        if (candidate == '}') {
            return '{';
        }
        if (candidate == ']') {
            return '[';
        }
        if (candidate == ')') {
            return '(';
        }

        return ' ';
    }
}
