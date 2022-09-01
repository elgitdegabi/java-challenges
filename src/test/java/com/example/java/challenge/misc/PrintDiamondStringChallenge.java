package com.example.java.challenge.misc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

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
     * PrintDiamondString class
     */
    public static class PrintDiamondString {

        /**
         * Gets {@link String} to print for given number n
         * @param n int
         * @return {@link String} to print
         */
        public static String print(final int n) {
            if (n > 0 && n % 2 != 0) {
                StringBuffer stringBuffer = new StringBuffer();

                for (int i = 1; i <= n; i+=2) {
                    stringBuffer.append(" ".repeat((n - i) / 2)).append("*".repeat(i)).append("\n");
                }

                for (int j = n - 2; j >= 1; j-=2) {
                    stringBuffer.append(" ".repeat((n - j) / 2)).append("*".repeat(j)).append("\n");
                }

                return stringBuffer.toString();
            }

            return null;
        }
    }

    /**
     * Unit test cases class
     */
    public static class PrintDiamondStringTest {
        @ParameterizedTest(name = "number: {0}")
        @MethodSource("basicTestCases")
        void parameterizedBasicTestCases(final int number, final String expected) {
            Assertions.assertEquals(expected, PrintDiamondString.print(number));
        }

        /**
         * Generates basic tests cases values
         * @return {@link Stream < Arguments >}
         */
        private static Stream<Arguments> basicTestCases() {
            return Stream.of(
                    arguments(2, null),
                    arguments(-2, null),
                    arguments(0, null),
                    arguments(1, "*\n"),
                    arguments(5, "  *\n ***\n*****\n ***\n  *\n"),
                    arguments(3, " *\n***\n *\n"),
                    arguments(15, "       *\n      ***\n     *****\n    *******\n   *********\n  ***********\n *************\n***************\n *************\n  ***********\n   *********\n    *******\n     *****\n      ***\n       *\n")
            );
        }
    }
}
