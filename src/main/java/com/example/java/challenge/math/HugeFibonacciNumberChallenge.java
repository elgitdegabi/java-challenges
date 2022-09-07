package com.example.java.challenge.math;

import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;

/**
 * The Nth Fibonacci algorithm: write an algorithm that can handle n up to 2000000.
 * Your algorithm must output the exact integer answer, to full precision. Also, it must correctly handle negative numbers as input.
 * HINT I: Can you rearrange the equation fib(n + 2) = fib(n + 1) + fib(n) to find fib(n) if you already know fib(n + 1) and fib(n + 2)?
 * Use this to reason what value fib has to have for negative values.
 * HINT II: See https://web.archive.org/web/20220614001843/https://mitpress.mit.edu/sites/default/files/sicp/full-text/book/book-Z-H-11.html#%_sec_1.2.4.
 * For example:
 * Case 1: 5 -> 5
 * Case 2: -5 -> 5
 * Case 2: -6 -> -8
 */
@Slf4j
public class HugeFibonacciNumberChallenge {

    /**
     * Calculates the Nth Fibonacci value from given number
     *
     * @param n {@link BigInteger} number
     * @return {@link BigInteger} the Nth Fibonacci value
     */
    public static BigInteger fib(BigInteger n) {
        if (n != null && BigInteger.ZERO.compareTo(n) != 0 && BigInteger.ONE.compareTo(n.abs()) != 0) {
            BigInteger[][] auxMatrix = {{BigInteger.ONE, BigInteger.ONE}, {BigInteger.ONE, BigInteger.ZERO}};
            BigInteger[][] resultMatrix = {{BigInteger.ONE, BigInteger.ONE}, {BigInteger.ONE, BigInteger.ZERO}};

            int currentNumber = n.abs().intValue();

            while (currentNumber > 0) {
                if (currentNumber % 2 == 1) {
                    resultMatrix = multiplyMatrix(resultMatrix, auxMatrix);
                }

                auxMatrix = multiplyMatrix(auxMatrix, auxMatrix);
                currentNumber = currentNumber / 2;
            }

            // IMPORTANT: this factor was "deducted" from hint 1
            BigInteger factor = BigInteger.valueOf(((BigInteger.ZERO.compareTo(n) > 0 && n.intValue() % 2 == 0) ? -1 : 1));

            return resultMatrix[1][1].multiply(factor);
        }

        return n;
    }

    /**
     * Multiplies given matrix1 and matrix2 (Cartesian product)
     *
     * @param matrix1 {@link BigInteger} matrix
     * @param matrix2 {@link BigInteger} matrix
     * @return {@link BigInteger} matrix
     */
    private static BigInteger[][] multiplyMatrix(final BigInteger matrix1[][], final BigInteger matrix2[][]) {
        return new BigInteger[][]{
                {multiplyValue(matrix1[0][0], matrix2[0][0], matrix1[0][1], matrix2[1][0]), multiplyValue(matrix1[0][0], matrix2[0][1], matrix1[0][1], matrix2[1][1])},
                {multiplyValue(matrix1[1][0], matrix2[0][0], matrix1[1][1], matrix2[1][0]), multiplyValue(matrix1[1][0], matrix2[0][1], matrix1[1][1], matrix2[1][1])}
        };
    }

    /**
     * Multiplies and sum given values (v1 * v2 + v3 * v4)
     *
     * @param value1 {@link BigInteger}
     * @param value2 {@link BigInteger}
     * @param value3 {@link BigInteger}
     * @param value4 {@link BigInteger}
     * @return {@link BigInteger} result of multiplies and sum
     */
    private static BigInteger multiplyValue(final BigInteger value1, final BigInteger value2, final BigInteger value3, final BigInteger value4) {
        return value1.multiply(value2).add(value3.multiply(value4));
    }
}
