package com.example.java.challenge.array;

import lombok.extern.slf4j.Slf4j;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Given an array of integers, calculate the ratios of its elements that are positive, negative, and zero.
 * Print the decimal value of each fraction on a new line with places after the decimal.
 * For example:
 * Case 1: -4 3 -9 0 4 1 ->  0.500000; 0.333333; 0.166667
 * Case 2: 1 2 3 -1 -2 -3 0 0 ->  0.375000; 0.375000; 0.250000
 */
@Slf4j
public class PlusMinusChallenge {
    public static void main(String[] args) {
        log.info("plus minus start");
        PlusMinus.calculate(List.of(-4, 3, -9, 0, 4, 1));
        log.info("plus minus end");
    }

    /**
     * Plus minus class
     */
    private static class PlusMinus {

        /**
         * Calculates positive, negative and zero rate based on given list
         * @param arr {@link List<Integer>}
         */
        public static void calculate(final List<Integer> arr) {
            double positive = arr.stream().filter(i -> i.intValue() > 0).count();
            double negative = arr.stream().filter(i -> i.intValue() < 0).count();
            double zero = arr.size() - positive - negative;

            DecimalFormat decimalFormat = new DecimalFormat("0.00000");
            decimalFormat.setRoundingMode(RoundingMode.HALF_EVEN);

            System.out.println(decimalFormat.format(positive / arr.size()));
            System.out.println(decimalFormat.format(negative / arr.size()));
            System.out.println(decimalFormat.format(zero / arr.size()));
        }
    }
}
