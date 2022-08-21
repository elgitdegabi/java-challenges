package com.example.java.challenge.array;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.IntStream;

/**
 * Given a ...
 * For example:
 * Case 1:
 */
@Slf4j
public class QueueOrderChaosChallenge {
    private static final int COUNT_RANGE = 4; // IMPORTANT: fix number range, for case 1 should be 0..3; for case 2: 0..100

    public static void main(String[] args) {
        log.info("QueueChaos start");
        QueueChaos.calculate(List.of(2, 1, 5, 3, 4));
        QueueChaos.calculate(List.of(1, 2, 5, 3, 4, 7, 8, 6));
        QueueChaos.calculate(List.of(1, 2, 5, 3, 7, 8, 6, 4));
        log.info("QueueChaos end");
    }

    /**
     * Counting Sort class
     */
    private static class QueueChaos {

        /**
         * Calculates counting sorting based on given list
         *
         * @param list {@link List<Integer>}
         * @return {@link List<Integer>} counting sort
         */
        public static void calculate(final List<Integer> list) {
            int totalBribes = 0;

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) - (i+1) > 2) {
                    System.out.println("Too chaotic");
                    return;
                }
            }

            Integer[] array = new Integer[list.size()];
            IntStream.range(0, array.length).forEach(i -> array[i] = list.get(i));

            for (int i = 1; i < array.length; i++) {
                if (array[i-1] > array[i]) {
                    Integer previous = array[i-1];
                    array[i-1] = array[i];
                    array[i] = previous;
                    totalBribes++;
                    i = 0;
                }
            }

            System.out.println(totalBribes);
        }
    }
}
