package com.example.java.challenge.array;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.regex.Pattern;

/**
 * Baseball Game
 * <p>
 * <p>
 * You are keeping score for a baseball game with strange rules. The game consists of several rounds, where the scores of past rounds may affect future rounds' scores.
 * <p>
 * At the beginning of the game, you start with an empty record. You are given a list of strings ops, where ops[i] is the ith operation you must apply to the record and is one of the following:
 * <p>
 * An integer x - Record a new score of x.
 * "+" - Record a new score that is the sum of the previous two scores. It is guaranteed there will always be two previous scores.
 * "D" - Record a new score that is double the previous score. It is guaranteed there will always be a previous score.
 * "C" - Invalidate the previous score, removing it from the record. It is guaranteed there will always be a previous score.
 * Return the sum of all the scores on the record..
 * For example:
 * Case 1: 5 2 C D + -> 5, 2 -> 5 -> 5, 10 -> 15
 */
@Slf4j
public class BaseBallWiredPointsChallenge {

    private static final Pattern NUMERIC_PATTERN = Pattern.compile("-?\\d+(\\.\\d+)?");

    /**
     * Calculates points from given values
     *
     * @param ops numeric or operation values
     * @return calculation result
     */
    public static int calPoints(final String[] ops) {
        if (ops.length > 0) {
            LinkedList<Integer> valueList = new LinkedList<>();

            for (int i = 0; i < ops.length; i++) {
                String current = ops[i];
                if (NUMERIC_PATTERN.matcher(current).matches()) {
                    valueList.add(Integer.valueOf(current));
                } else if (valueList.size() > 0 && current.equalsIgnoreCase("C")) {
                    valueList.removeLast();
                } else if (valueList.size() > 0 && current.equalsIgnoreCase("D")) {
                    valueList.add(valueList.getLast() * 2);
                } else if (current.equalsIgnoreCase("+") && valueList.size() > 1) {
                    valueList.add(valueList.getLast() + valueList.get(valueList.size() - 2));
                }
            }

            return valueList.stream().mapToInt(Integer::intValue).sum();
        }

        return 0;
    }
}
