package com.example.java.challenge.array;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Write a method that takes an array of consecutive (increasing) letters as input and that returns the missing letter in the array.
 * You will always get an valid array. And it will be always exactly one letter be missing. The length of the array will always be at least 2.
 * The array will always contain letters in only one case.
 * Note: use the English alphabet with 26 letters.
 * For example:
 * Case 1: ['a','b','c','d','f'] -> 'e'
 * Case 2: ['O','Q','R','S'] -> 'P'
 */
@Slf4j
public class MissingLetterChallenge {

    /**
     * MissingLetter class
     */
    public static class MissingLetter {

        /**
         * Finds missing letter from given char array
         * @param array char array
         * @return missing letter or blank
         */
        public static char findMissingLetter(final char[] array) {
            for (int i = 1; i < array.length; i++) {
                if(array[i] - array[i-1] != 1){
                    return (char)(array[i-1] + 1);
                }
            }

            return ' ';
        }
    }

    /**
     * Unit test cases class
     */
    public static class MissingLetterTest {
        @Test
        void testCase1() {
            Assertions.assertEquals('e', MissingLetter.findMissingLetter(new char[]{'a', 'b', 'c', 'd', 'f'}));
        }

        @Test
        void testCase2() {
            Assertions.assertEquals('P', MissingLetter.findMissingLetter(new char[]{'O', 'Q', 'R', 'S'}));
        }
    }
}
