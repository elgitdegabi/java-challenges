package com.example.java.challenge.misc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Create a NxN spiral with a given size.
 * Return value should contain array of arrays, of 0 and 1, with the first row being composed of 1s.
 * Notes:
 * - Because of the edge-cases for tiny spirals, the size will be at least 5.
 * - General rule-of-a-thumb is, that the snake made with '1' cannot touch to itself.
 * For example:
 * Case 1: 5 -> [[1,1,1,1,1],[0,0,0,0,1],[1,1,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
 */
@Slf4j
public class CreateSpiralChallenge {

    /**
     * CreateSpiral class
     */
    public static class CreateSpiral {

        /**
         * Creates size x size matrix populated with 1 and 0 values
         *
         * @param size int
         * @return size x size matrix populated with 1 and 0 values
         */
        public static int[][] spiralizer(final int size) {
            if (size >= 5) {
                int[][] matrix = new int[size][size];
                int middleShift = size % 4 == 0 ? 1 : 0; // if size % 4 spiral finishes under middle line
                int cellValue = 1; // cell value 1 (initially)

                for (int row = 0; row <= size / 2; row++) {
                    for (int col = row; col < size - row; col++) {
                        matrix[row][col] = cellValue; // move right
                        matrix[col][size - 1 - row] = cellValue; // move down
                        matrix[size - 1 - row][size - 1 - col] = cellValue; // move left
                        matrix[size - 1 - col][row] = cellValue; // move up
                    }

                    if (row + middleShift < size / 2) {
                        cellValue = 1 - cellValue;
                        matrix[row + 1][row] = cellValue;
                    }
                }

                return matrix;
            }

            return null;
        }
    }

    /**
     * Unit test cases class
     */
    public static class CreateSpiralTest {
        @ParameterizedTest(name = "size: {0}")
        @MethodSource("basicTestCases")
        void parameterizedBasicTestCases(final int size, final int[][] expected) {
            Assertions.assertArrayEquals(expected, CreateSpiral.spiralizer(size));
        }

        /**
         * Generates basic tests cases values
         *
         * @return {@link Stream < Arguments >}
         */
        private static Stream<Arguments> basicTestCases() {
            return Stream.of(
                    arguments(3, null),
                    arguments(-2, null),
                    arguments(5, new int[][]{
                            {1, 1, 1, 1, 1},
                            {0, 0, 0, 0, 1},
                            {1, 1, 1, 0, 1},
                            {1, 0, 0, 0, 1},
                            {1, 1, 1, 1, 1}}),
                    arguments(8, new int[][]{
                            {1, 1, 1, 1, 1, 1, 1, 1},
                            {0, 0, 0, 0, 0, 0, 0, 1},
                            {1, 1, 1, 1, 1, 1, 0, 1},
                            {1, 0, 0, 0, 0, 1, 0, 1},
                            {1, 0, 1, 0, 0, 1, 0, 1},
                            {1, 0, 1, 1, 1, 1, 0, 1},
                            {1, 0, 0, 0, 0, 0, 0, 1},
                            {1, 1, 1, 1, 1, 1, 1, 1}}),
                    arguments(10, new int[][]{
                            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                            {0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                            {1, 1, 1, 1, 1, 1, 1, 1, 0, 1},
                            {1, 0, 0, 0, 0, 0, 0, 1, 0, 1},
                            {1, 0, 1, 1, 1, 1, 0, 1, 0, 1},
                            {1, 0, 1, 0, 0, 1, 0, 1, 0, 1},
                            {1, 0, 1, 0, 0, 0, 0, 1, 0, 1},
                            {1, 0, 1, 1, 1, 1, 1, 1, 0, 1},
                            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}})
            );
        }
    }
}
