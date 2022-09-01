package com.example.java.challenge.array;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.IntStream;

/**
 * Determine the minimum number of bribes that took place to get to a given queue order.
 * Each person wears a sticker indicating their initial position in the queue from 1 to n.
 * Any person can bribe the person directly in front of them to swap positions, but they still wear their original sticker.
 * One person can bribe at most two others.
 * Print the number of bribes, or, if anyone has bribed more than two people, print Too chaotic.
 * <p>
 * IMPORTANT: AKA Queue Order Chaos Challenge
 * <p>
 * For example:
 * Case 1: 2, 1, 5, 3, 4 -> 3
 * Case 2: 2, 5, 1, 3, 4 -> Too chaotic
 * Case 3: 5, 1, 2, 3, 7, 8, 6, 4 -> Too chaotic
 * Case 4: 1, 2, 5, 3, 7, 8, 6, 4 -> 7
 * Case 5: 1, 2, 5, 3, 4, 7, 8, 6 -> 4
 */
@Slf4j
public class MinimumBribesChallenge {

    /**
     * Calculates minimum quantity of bribes from given list
     *
     * @param q {@link List<Integer>}
     * @return {@link String} with quantity of bribes or Too chaotic
     */
    public static String minimumBribes(final List<Integer> q) {
        long totalBribes = 0;

        for (int i = 0; i < q.size(); i++) {
            if (q.get(i) - (i + 1) > 2) { // diff between current position value & proper position > 2 -> jumps more than 2 positions
                return "Too chaotic";
            }
        }

        Integer[] array = new Integer[q.size()];
        IntStream.range(0, array.length).forEach(i -> array[i] = q.get(i));

        for (int i = 1; i < array.length; i++) {
            if (array[i - 1] > array[i]) { // reorder array and continues from the same position
                Integer previous = array[i - 1];
                array[i - 1] = array[i];
                array[i] = previous;

                totalBribes++;
                i = (i == 1 ? 0 : i - 2);
            }
        }

        return String.valueOf(totalBribes);
    }
}
