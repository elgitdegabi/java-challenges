package com.example.java.challenge.array;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.PriorityQueue;

/**
 * Jesse loves cookies and wants the sweetness of some cookies to be greater than value K.
 * To do this, two cookies with the least sweetness are repeatedly mixed.
 * This creates a special combined cookie with:
 * - sweetness = (1 x  Least sweet cookie) + (2 x  2nd least sweet cookie).
 * - This occurs until all the cookies have a sweetness >= K
 * Given the sweetness of a number of cookies, determine the minimum number of operations required.
 * If it is not possible, return - 1.
 * For example
 * Case 1: K = 9 & List.of(2, 7, 3, 6, 4, 6) -> 4
 */
@Slf4j
public class JesseCookiesChallenge {

    /**
     * Executes and calculates the minimum number of operations required for given K and list of values
     *
     * @param k int limit of sweatness
     * @param A {@link List} of values
     * @return minimum number of operations required
     */
    public static int execute(final int k, final List<Integer> A) {
        if (k < 1 || A.size() < 1) {
            return -1;
        }

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(A);
        int operationCount = 0;

        while (priorityQueue.peek() < k) {
            if (priorityQueue.size() < 2) {
                return -1;
            }

            priorityQueue.add(priorityQueue.remove() + 2 * priorityQueue.remove()); // instead of poll
            operationCount++;
        }

        return operationCount;
    }
}
