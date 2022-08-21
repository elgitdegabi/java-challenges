package com.example.java.challenge.array;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.PriorityQueue;

/**
 * Jesse loves cookies and wants the sweetness of some cookies to be greater than value
 *
 * . To do this, two cookies with the least sweetness are repeatedly mixed. This creates a special combined cookie with:
 *
 * sweetness
 * Least sweet cookie
 *
 * 2nd least sweet cookie).
 *
 * This occurs until all the cookies have a sweetness
 *
 * .
 *
 * Given the sweetness of a number of cookies, determine the minimum number of operations required. If it is not possible, return
 * .
 */
@Slf4j
public class JesseCookiesChallenge {
    public static void main(String[] args) {
        log.info("Jesse Cookies start");
        log.info("Jesse Cookies result: {}", JesseCookie.execute(7, List.of(1, 2, 3, 9, 10, 12)));
        log.info("Jesse Cookies result: {}", JesseCookie.execute(1000, List.of(52, 96, 13, 37)));
        log.info("Jesse Cookies result: {}", JesseCookie.execute(105823341, List.of(52, 96, 13, 37)));
        log.info("Jesse Cookies end");
    }

    /**
     * JesseCookie class
     */
    private static class JesseCookie {

        /**
         *
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
}
