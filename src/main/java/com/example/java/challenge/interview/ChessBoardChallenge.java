package com.example.java.challenge.interview;

import lombok.extern.slf4j.Slf4j;

/**
 * Draw a chess RxC board
 * If row size is not equal to column size (R != C), throw an exception
 */
@Slf4j
public class ChessBoardChallenge {
    public static void main(String[] args) {
        log.info("chess board start");
        ChessBoard.printBoard(5, 5);
        log.info("chess board end");
    }

    /**
     * Chess board class
     */
    private static class ChessBoard {

        /**
         * Prints board from given row size and column size
         * Important: if row size is not equal to column size, it throws an {@link IllegalArgumentException}
         * @param rowSize int
         * @param columnSize int
         */
        public static void printBoard(final int rowSize, final int columnSize) {
            if (rowSize != columnSize) {
                throw new IllegalArgumentException("row size and column size should be equal");
            }

            for (int i = 0; i < rowSize; i++) {
                StringBuffer stringBuffer = new StringBuffer();

                for (int j = 0; j < columnSize; j++) {
                    stringBuffer.append((i % 2 == j % 2 ? "X" : " "));
                }

                log.info(stringBuffer.toString());
            }
        }
    }
}
