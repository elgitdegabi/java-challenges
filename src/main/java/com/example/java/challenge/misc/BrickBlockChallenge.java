package com.example.java.challenge.misc;

import lombok.extern.slf4j.Slf4j;

/**
 * Given an infinite number of 4 types of lego blocks of sizes given as (depth x height x width).
 * Using these blocks we need to make a wall of height n and width m. and remember that wall should not have any holes in it
 * and should be one solid structure and bricks must be laid horizontally.
 * All the permutations of the wall are not valid so we need to find and print the number of valid wall formations.
 * Valid bricks dimensions: deep 1..1; height 1..1; width 1..4;
 * Parameter(s):
 * - int n: the height of the wall
 * - int m: the width of the wall
 * Returns
 * - int: the number of valid wall formations modulo (10^9 + 7)
 * <p>
 * For example:
 * Case 1: 1, 2 -> 1
 * Case 2: 1, 3 -> 1
 * Case 3: 1, 4 -> 1
 * Case 4: 2, 2 -> 3
 * Case 5: 4, 5 -> 35714
 * Case 6: 4, 6 -> 447902
 * Case 7: 4, 7 -> 5562914
 * Case 8: 694, 335 -> 30314890
 */
@Slf4j
public class BrickBlockChallenge {

    final static int FORMATION_MOD = (int) Math.pow(10, 9) + 7; // provided factor or modulo: (10^9 + 7)

    /**
     * Calculates number of Lego blocks valid combinations based on given n (height) and m (width) parameters
     *
     * @param n int height
     * @param m int width
     * @return number of Lego blocks valid combinations
     */
    public static int legoBlocks(final int n, final int m) {
        long[] oneRowCombination = getOneRowCombination(m);
        long[] allPossibleCombination = getAllCombination(oneRowCombination, n, m);
        long[] validCombination = getValidCombination(allPossibleCombination, m);
        return (int) (validCombination[m - 1] % FORMATION_MOD);
    }

    /**
     * Calculates number of Lego blocks combinations for one row of given width
     * IMPORTANT: one block width should be 1..4
     *
     * @param width int width
     * @return number of Lego blocks combinations for one row
     */
    private static long[] getOneRowCombination(final int width) {
        long[] rowCombination = new long[width];

        for (int i = 0; i < width; i++) {
            if (i < 2) {
                rowCombination[i] = i + 1; // blocks 1 & 2 -> 1 & 2 possible combinations
            } else if (i < 4) {
                rowCombination[i] = (i / 2 + i % 2) * 4; // blocks 3 & 4 -> 4 & 8 possible combinations
            } else {
                rowCombination[i] = (rowCombination[i - 1] + rowCombination[i - 2] + rowCombination[i - 3] + rowCombination[i - 4]) % FORMATION_MOD;
            }
        }

        return rowCombination;
    }

    /**
     * Calculates number of Lego blocks combinations for the wall (height x width)
     *
     * @param oneRowCombination long[] one row combinations array
     * @param height            int height
     * @param width             int width
     * @return number of Lego blocks combinations for the wall
     */
    private static long[] getAllCombination(final long[] oneRowCombination, final int height, final int width) {
        long[] allPossibleCombination = new long[width];

        for (int i = 0; i < width; i++) {
            allPossibleCombination[i] = oneRowCombination[i];

            for (int j = 1; j < height; j++) {
                allPossibleCombination[i] = (allPossibleCombination[i] * oneRowCombination[i]) % FORMATION_MOD;
            }
        }

        return allPossibleCombination;
    }

    /**
     * Calculates number of VALID Lego blocks combinations for the wall
     *
     * @param allPossibleCombination long[] all possible combinations array
     * @param width                  int width
     * @return number of VALID Lego blocks combinations for the wall
     */
    private static long[] getValidCombination(final long[] allPossibleCombination, final int width) {
        long[] validCombination = new long[width];

        for (int i = 0; i < width; i++) {
            validCombination[i] = allPossibleCombination[i];

            for (int j = 0; j < i; j++) {
                validCombination[i] -= validCombination[j] * allPossibleCombination[i - j - 1] % FORMATION_MOD;

                if (validCombination[i] < 0) {
                    validCombination[i] += FORMATION_MOD;
                }
            }
        }

        return validCombination;
    }
}
