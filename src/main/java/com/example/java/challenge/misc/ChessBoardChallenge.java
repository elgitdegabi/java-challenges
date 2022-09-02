package com.example.java.challenge.misc;

import lombok.extern.slf4j.Slf4j;

/**
 * Draw a chess RxC board
 * If row size is not equal to column size (R != C), throw an exception
 */
@Slf4j
public class ChessBoardChallenge {

    /**
     * Prints board from given row size and column size
     * Important: if row size is not equal to column size, it throws an {@link IllegalArgumentException}
     *
     * @param rowSize    int
     * @param columnSize int
     * @return {@link String} board
     */
    public static String createBoard(final int rowSize, final int columnSize) {
        if (rowSize != columnSize) {
            throw new IllegalArgumentException("row size and column size should be equal");
        }

        StringBuffer board = new StringBuffer();
        for (int i = 0; i < rowSize; i++) {
            StringBuffer line = new StringBuffer();

            for (int j = 0; j < columnSize; j++) {
                line.append((i % 2 == j % 2 ? "X" : " "));
            }

            log.info(line.toString());
            board.append(line).append("\n");
        }

        return board.toString();
    }
}
