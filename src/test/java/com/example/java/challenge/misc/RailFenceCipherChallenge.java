package com.example.java.challenge.misc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Create two functions to encode and then decode a string using the Rail Fence Cipher.
 * This cipher is used to encode a string by placing each character successively in a diagonal along a set of "rails".
 * First start off moving diagonally and down. When you reach the bottom, reverse direction and move diagonally and up until you reach the top rail.
 * Continue until you reach the end of the string. Each "rail" is then read left to right to derive the encoded string..
 * Notes:
 * - For both encoding and decoding, assume number of rails >= 2
 * - Passing an empty string will return an empty string.
 * - Don't filter out punctuation as they are a part of the string.
 * For example:
 * Case 1:  "Hello, World!", 3 -> "Hoo!el,Wrdl l"
 */
@Slf4j
public class RailFenceCipherChallenge {

    /**
     * RailFenceCipher class
     */
    public static class RailFenceCipher {

        /**
         * Encodes given {@link String} based on rail-fence (or zig-zag) strategy.
         * Note: creates a matrix of N x Text.length, writes the text following a zig-zag pattern and concatenates
         * each matrix's row as encoded text
         * @param s {@link String} text
         * @param n size of rail fence
         * @return  {@link String} encoded text
         */
        public static String encode(final String s, final int n) {
            if (n > 1 && s != null && s.trim().length() > 1) {
                char[][] matrix = new char[n][s.length()];
                int rowShift = -1, row = 0;

                for (int i = 0; i < s.length(); i++) {
                    matrix[row][i] = s.charAt(i);

                    if (i % (n - 1) == 0) {
                        rowShift = rowShift * -1;
                    }

                    row = row + rowShift;
                }

                StringBuffer stringBuffer = new StringBuffer();
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < s.length(); j++) {
                        if (Character.MIN_VALUE != matrix[i][j]) {
                            stringBuffer.append(matrix[i][j]);
                        }
                    }
                }

                return stringBuffer.toString();
            }

            return s;
        }

        /**
         * Decodes given {@link String} based on rail-fence (or zig-zag) strategy.
         * Note: creates a matrix of N x Text.length, writes # value following a zig-zag pattern and finds the match between
         * current text column and matrix's # values. Finally concatenates each matrix's row as decoded text
         * @param s {@link String} text
         * @param n size of rail fence
         * @return  {@link String} decoded text
         */
        public static String decode(final String s, final int n) {
            if (n > 1 && s != null && s.trim().length() > 1) {
                char[][] matrix = new char[n][s.length()];
                int rowShift = -1, row = 0;

                for (int i = 0; i < s.length(); i++) {
                    matrix[row][i] = '#';

                    if (i % (n - 1) == 0) {
                        rowShift = rowShift * -1;
                    }

                    row = row + rowShift;
                }

                int index = 0;
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < s.length(); j++) {
                        if (matrix[i][j] == '#') {
                            matrix[i][j] = s.charAt(index);
                            index++;
                        }
                    }

                }

                StringBuffer stringBuffer = new StringBuffer();
                rowShift = -1;
                row = 0;

                for (int i = 0; i < s.length(); i++) {
                    if (Character.MIN_VALUE != matrix[row][i]) {
                        stringBuffer.append(matrix[row][i]);
                    }
                    if (i % (n - 1) == 0) {
                        rowShift = rowShift * -1;
                    }

                    row = row + rowShift;
                }

                return stringBuffer.toString();
            }

            return s;
        }
    }

    /**
     * Unit test cases class
     */
    public static class CreateSpiralTest {
        @ParameterizedTest(name = "text: {0} - rail: {1}")
        @MethodSource("basicTestCases")
        void parameterizedBasicTestCases(final String text, final int rail, final String expected) {
            Assertions.assertEquals(expected, RailFenceCipher.encode(text, rail));
            Assertions.assertEquals(text, RailFenceCipher.decode(expected, rail));
        }

        /**
         * Generates basic tests cases values
         *
         * @return {@link Stream < Arguments >}
         */
        private static Stream<Arguments> basicTestCases() {
            return Stream.of(
                    arguments("", 3, ""),
                    arguments("Hello, World!", 3, "Hoo!el,Wrdl l"),
                    arguments("Hello, World!", 4, "H !e,Wdloollr"),
                    arguments("WEAREDISCOVEREDFLEEATONCE", 3, "WECRLTEERDSOEEFEAOCAIVDEN")
            );
        }
    }
}
