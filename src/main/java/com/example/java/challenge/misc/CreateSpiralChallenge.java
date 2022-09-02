package com.example.java.challenge.misc;

import lombok.extern.slf4j.Slf4j;

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
