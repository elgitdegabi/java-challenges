package com.example.java.challenge.math;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Given a positive integer n written as abcd... (a, b, c, d... being digits) and a positive integer p we want to find
 * a positive integer k, if it exists, such that the sum of the digits of n taken to the successive powers of p is equal to k * n.
 * In other words: Is there an integer k such as : (a ^ p + b ^ (p+1) + c ^(p+2) + d ^ (p+3) + ...) = n * k
 * If it is the case we will return k, if not return -1.
 * Note: n and p will always be given as strictly positive integers..
 * For example:
 * Case 1: 89 & 1-> 8**1 + 9** 2 -> 89 % 89 = 0 so 89 / 89 -> 1
 */
@Slf4j
public class DigPowChallenge {

    /**
     * DigPow class
     */
    public static class DigPow {

        /**
         * Calculates "the digit" from given n and p
         * @param n int
         * @param p int
         * @return "the digit"
         */
        public static long digPow(final int n, final int p) {
            int[] digits = Arrays.stream(String.valueOf(n).split("")).mapToInt(val -> Integer.valueOf(val).intValue()).toArray();
            int powIndex = p;
            long result = 0;

            for (int i = 0; i < digits.length; i++) {
                result += Math.pow(digits[i], powIndex++);
            }

            return (result % n == 0 ? result / n : -1);
        }
    }

    /**
     * Unit test cases class
     */
    public static class DigPowTest {
        @Test
        void testCase1() {
            Assertions.assertEquals(1, DigPow.digPow(89, 1));
        }

        @Test
        void testCase2() {
            Assertions.assertEquals(-1, DigPow.digPow(92, 1));
        }

        @Test
        void testCase3() {
            Assertions.assertEquals(51, DigPow.digPow(46288, 3));
        }

        @Test
        void testCase4() {
            Assertions.assertEquals(2, DigPow.digPow(695, 2));
        }

        @Test
        void testCase5() {
            Assertions.assertEquals(-1, DigPow.digPow(699, 2));
        }
    }
}
