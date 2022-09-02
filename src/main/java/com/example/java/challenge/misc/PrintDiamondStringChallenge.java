package com.example.java.challenge.misc;

import lombok.extern.slf4j.Slf4j;

/**
 * You need to return a string that looks like a diamond shape when printed on the screen, using asterisk (*) characters.
 * Trailing spaces should be removed, and every line must be terminated with a newline character (\n).
 * Return null/nil/None/... if the input is an even number or negative, as it is not possible to print a diamond of even or negative size.
 * For example:
 * Case 1: 3 -> *\n, ***, *\n
 */
@Slf4j
public class PrintDiamondStringChallenge {

    /**
     * Gets {@link String} to print for given number n
     *
     * @param n int
     * @return {@link String} to print
     */
    public static String print(final int n) {
        if (n > 0 && n % 2 != 0) {
            StringBuffer stringBuffer = new StringBuffer();

            for (int i = 1; i <= n; i += 2) {
                stringBuffer.append(" ".repeat((n - i) / 2)).append("*".repeat(i)).append("\n");
            }

            for (int j = n - 2; j >= 1; j -= 2) {
                stringBuffer.append(" ".repeat((n - j) / 2)).append("*".repeat(j)).append("\n");
            }

            return stringBuffer.toString();
        }

        return null;
    }
}
