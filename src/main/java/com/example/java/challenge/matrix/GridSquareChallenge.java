package com.example.java.challenge.matrix;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a square grid of characters in the range ascii[a-z], rearrange elements of each row alphabetically, ascending.
 * Determine if the columns are also in ascending alphabetical order, top to bottom.
 * Return YES if they are or NO if they are not..
 * For example:
 * Case 1: "abc", "lmp", "qrt" -> YES
 * Case 2: "mpxz", "abcd", "wlmf" -> NO
 * Case 3: "abc", "hjk", "mpq", "rtv" -> YES
 */
@Slf4j
public class GridSquareChallenge {
    public static void main(String[] args) {
        log.info("GridSquare start");
        log.info("GridSquare - result: {}", GridSquare.isInAscendingAlphabeticalOrder(List.of("abc", "lmp", "qrt")));
        log.info("GridSquare - result: {}", GridSquare.isInAscendingAlphabeticalOrder(List.of("mpxz", "abcd", "wlmf")));
        log.info("GridSquare - result: {}", GridSquare.isInAscendingAlphabeticalOrder(List.of("abc", "hjk", "mpq", "rtv")));
        log.info("GridSquare end");
    }

    /**
     * GridSquare class
     */
    private static class GridSquare {

        /**
         * Verifies if given list is in ascending alphabetical order
         *
         * @param grid {@link List<String>}
         * @return YES or NO
         */
        public static String isInAscendingAlphabeticalOrder(final List<String> grid) {
            List<String> sortedList = new ArrayList();

            for (int i = 0; i < grid.size(); i++) {
                char[] temp = grid.get(i).toCharArray();
                Arrays.sort(temp);
                sortedList.add(String.valueOf(temp));
            }

            for (int i = 1; i < sortedList.size(); i++) {
                for (int j = 0; j < sortedList.get(i).length(); j++) {
                    if (sortedList.get(i - 1).charAt(j) > sortedList.get(i).charAt(j)) {
                        return "NO";
                    }
                }
            }
            return "YES";
        }
    }
}
