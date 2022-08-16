package com.example.java.challenge.matrix;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.IntStream;

/**
 * Given a matrix where each cell of the matrix contains an integer, you can reverse any of its rows or columns any number of times.
 * The goal of the game is to maximize the sum of the elements in the sub-matrix located in the upper-left quadrant of the matrix.
 * For example:
 * Case 1: 1, 2; 3, 4; -> 4
 * Case 2: 112, 42, 83, 119; 56, 125, 56, 49; 15, 78, 101, 43; 62, 98, 114, 108 -> 119 + 114 + 56 + 125 = 414
 * Case 3: 107, 54, 128, 15; 12, 75, 110, 138; 100, 96, 34, 85; 75, 15, 28, 112 -> 112 + 128 + 138 + 110 = 488
 *
 * Optimization tip:
 * . iterate only until matrix size / 2 (n size parameter), because your are compare "opposite" rows and columns
 * . Compare matrix columns between [i,j] && [i, matrix.size()-j-1]
 * . Compare matrix rows between [matrix.size()-i-1, j] && [matrix.size()-i-1, matrix.size()-j-1]
 * . avoid rotation operations -> goal is only sum maximum values not rotate matrix's rows or columns
 */
@Slf4j
public class FlipMatrixChallenge {
    public static void main(String[] args) {
        log.info("flip matrix start");

        //log.info("flip matrix maximum is: {}", FlipMatrix.calculate(List.of(List.of(112, 42, 83, 119), List.of(56, 125, 56, 49), List.of(15, 78, 101, 43), List.of(62, 98, 114, 108))));
        log.info("flip matrix maximum is: {}", FlipMatrix.calculate(List.of(List.of(107, 54, 128, 15), List.of(12, 75, 110, 138), List.of(100, 96, 34, 85), List.of(75, 15, 28, 112))));

        log.info("flip matrix end");
    }

    /**
     * Flip matrix class
     */
    private static class FlipMatrix {

        /**
         * Calculate maximize sum of the elements in the sub-matrix located in the upper-left quadrant from given matrix
         *
         * @param matrix {@link List<List<Integer>>}
         * @return maximize sum of the elements in the sub-matrix located in the upper-left quadrant
         */
        public static int calculate(final List<List<Integer>> matrix) {
            if (matrix == null || matrix.size() % 2 != 0 || matrix.size() != matrix.get(0).size()) {
                throw new IllegalArgumentException("matrix size is not valid: should be 2nX2n square matrix");
            }

            return IntStream.range(0, matrix.size() / 2)
                    .flatMap(i -> IntStream.range(0, matrix.size() / 2).map(j ->
                            Math.max(
                                    Math.max(matrix.get(i).get(j), matrix.get(i).get(matrix.size() - j - 1)),
                                    Math.max(matrix.get(matrix.size() - i - 1).get(j), matrix.get(matrix.size() - i - 1).get(matrix.size() - j - 1)))
                    )).sum();
        }
    }
}
