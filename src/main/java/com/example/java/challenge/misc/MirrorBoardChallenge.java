package com.example.java.challenge.misc;

import lombok.extern.slf4j.Slf4j;

/**
 * Draw a mirror RxC board
 * If row size is not equal to column size (R != C), throw an exception
 */
@Slf4j
public class MirrorBoardChallenge {
    public static void main(String[] args) {
        log.info("mirror board start");
        MirrorBoard.printBoard(6, 6);
        MirrorBoard.printBoard(5, 5);
        log.info("mirror board end");
    }

    /**
     * Mirror board class
     */
    private static class MirrorBoard {

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

            log.info("-----------------------------------------------------------------------------------------------");
            StringBuffer stringBuffer = new StringBuffer();

            for (int i = 0; i < rowSize; i++) {
                stringBuffer.delete(0, stringBuffer.length());

                for (int j = 0; j < columnSize; j++) {
                    stringBuffer.append((i == j || (rowSize - 1 - i == j)) ? "X" : " ");
                }

                log.info(stringBuffer.toString());
            }

            log.info("-----------------------------------------------------------------------------------------------");
        }
    }
}
