package com.example.java.challenge.string;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Complete the function that accepts a string parameter, and reverses each word in the string.
 * All spaces in the string should be retained.
 * For example:
 * Case 1: "double  spaces" -> "elbuod  secaps"
 */
@Slf4j
public class ReverseWordsChallenge {

    /**
     * ReverseWord class
     */
    public static class ReverseWord {

        /**
         * Reverse words from given original text
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

    /**
     * Unit test cases class
     */
    public static class ReverseWordsTest {
        @Test
        void testCase1() {
            Assertions.assertEquals("ehT kciuq nworb xof spmuj revo eht yzal .god", ReverseWord.reverseWords("The quick brown fox jumps over the lazy dog."));

        }

        @Test
        void testCase2() {
            Assertions.assertEquals("elppa", ReverseWord.reverseWords("apple"));
        }

        @Test
        void testCase3() {
            Assertions.assertEquals("a b c d", ReverseWord.reverseWords("a b c d"));
        }

        @Test
        void testCase4() {
            Assertions.assertEquals("elbuod  decaps  sdrow", ReverseWord.reverseWords("double  spaced  words"));
        }

        @Test
        void testCase5() {
            Assertions.assertEquals("   ", ReverseWord.reverseWords("   "));
        }
    }
}
