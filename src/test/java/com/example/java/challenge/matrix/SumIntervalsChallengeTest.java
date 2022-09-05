package com.example.java.challenge.matrix;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Unit test cases class for {@link SumIntervalsChallenge}
 */
public class SumIntervalsChallengeTest {

    @ParameterizedTest(name = "intervals: {0} - expected: {1}")
    @MethodSource("basicTestCases")
    void parameterizedBasicTestCases(final int[][] intervalMatrix, final int expected) {
        Assertions.assertEquals(expected, SumIntervalsChallenge.sumIntervals(intervalMatrix));
    }

    /**
     * Generates basic tests cases values
     *
     * @return {@link Stream < Arguments >}
     */
    private static Stream<Arguments> basicTestCases() {
        return Stream.of(
                arguments(new int[][]{}, 0),
                arguments(new int[][]{{1, 4}, {6, 5}}, 0),
                arguments(new int[][]{{1, 4, 5}, {3, 5}}, 0),
                arguments(new int[][]{{1, 4}, {1, 3}}, 3),
                arguments(new int[][]{{-1000000000, 1000000000}}, 2000000000),
                arguments(new int[][]{{2, 5}, {1, 4}, {3, 10}}, 9),
                arguments(new int[][]{{1, 4}, {7, 10}, {3, 5}}, 7),
                arguments(new int[][]{{4, 4}, {6, 6}, {8, 8}}, 0),
                arguments(new int[][]{{1, 2}, {6, 10}, {11, 15}}, 9),
                arguments(new int[][]{{4, 8}, {9, 10}, {15, 21}}, 11),
                arguments(new int[][]{{-1, 4}, {-5, -3}}, 7),
                arguments(new int[][]{{-245, -218}, {-194, -179}, {-155, -119}}, 78),
                arguments(new int[][]{{0, 20}, {-100_000_000, 10}, {30, 40}}, 100_000_030),
                arguments(new int[][]{{1, 2}, {2, 6}, {6, 55}}, 54),
                arguments(new int[][]{{-2, -1}, {-1, 0}, {0, 21}}, 23),
                arguments(new int[][]{{1, 4}, {7, 10}, {3, 5}}, 7),
                arguments(new int[][]{{5, 8}, {3, 6}, {1, 2}}, 6),
                arguments(new int[][]{{1, 5}, {10, 20}, {1, 6}, {16, 19}, {5, 11}}, 19),
                arguments(new int[][]{{2, 5}, {-1, 2}, {-40, -35}, {6, 8}}, 13),
                arguments(new int[][]{{-7, 8}, {-2, 10}, {5, 15}, {2000, 3150}, {-5400, -5338}}, 1234),
                arguments(new int[][]{{-101, 24}, {-35, 27}, {27, 53}, {-105, 20}, {-36, 26},}, 158)
        );
    }
}
