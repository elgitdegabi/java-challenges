package com.example.java.challenge.misc;

import lombok.extern.slf4j.Slf4j;

/**
 * Draw a mirror RxC board
 * If row size is not equal to column size (R != C), throw an exception
 */
@Slf4j
public class MirrorBoardChallenge {

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
        StringBuffer line = new StringBuffer();
        for (int i = 0; i < rowSize; i++) {
            line.delete(0, line.length());

            for (int j = 0; j < columnSize; j++) {
                line.append((i == j || (rowSize - 1 - i == j)) ? "X" : " ");
            }

            log.info(line.toString());
            board.append(line).append("\n");
        }

        return board.toString();
    }
}
