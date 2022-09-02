package com.example.java.challenge.misc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Unit test cases class for {@link MultiFloorLiftChallenge}
 */
@Slf4j
public class MultiFloorLiftChallengeTest {

    @ParameterizedTest(name = "scenario: {0}")
    @MethodSource("basicTestCases")
    void parameterizedBasicTestCases(final String scenario, final int[][] queue, final int size, final int[] expected) {
        log.info("capacity: {} - expected: {}", size, expected);
        Assertions.assertArrayEquals(expected, MultiFloorLiftChallenge.theLift(queue, size));
    }

    /**
     * Generates basic tests cases values
     *
     * @return {@link Stream < Arguments >}
     */
    private static Stream<Arguments> basicTestCases() {
        return Stream.of(
                arguments("testUp", new int[][]{
                        new int[0], // G
                        new int[0], // 1
                        new int[]{5, 5, 5}, // 2
                        new int[0], // 3
                        new int[0], // 4
                        new int[0], // 5
                        new int[0], // 6
                }, 5, new int[]{0, 2, 5, 0}),
                arguments("testDown", new int[][]{
                        new int[0], // G
                        new int[0], // 1
                        new int[]{1, 1}, // 2
                        new int[0], // 3
                        new int[0], // 4
                        new int[0], // 5
                        new int[0], // 6
                }, 5, new int[]{0, 2, 1, 0}),
                arguments("testUpAndUp", new int[][]{
                        new int[0], // G
                        new int[]{3}, // 1
                        new int[]{4}, // 2
                        new int[0], // 3
                        new int[]{5}, // 4
                        new int[0], // 5
                        new int[0], // 6
                }, 5, new int[]{0, 1, 2, 3, 4, 5, 0}),
                arguments("testDownAndDown", new int[][]{
                        new int[0], // G
                        new int[]{0}, // 1
                        new int[0], // 2
                        new int[0], // 3
                        new int[]{2}, // 4
                        new int[]{3}, // 5
                        new int[0], // 6
                }, 5, new int[]{0, 5, 4, 3, 2, 1, 0}),
                arguments("random1", new int[][]{
                        new int[]{1}, // G
                        new int[]{0, 0, 0}, // 1
                        new int[0], // 2
                }, 3, new int[]{0, 1, 0}),
                arguments("random2", new int[][]{
                        new int[0], // 0
                        new int[0], // 1
                        new int[]{4, 4, 4, 4}, // 2
                        new int[0], // 3
                        new int[]{2, 2, 2, 2}, // 4
                        new int[0], // 5
                        new int[0] // 6
                }, 2, new int[]{0, 2, 4, 2, 4, 2, 0}),
                arguments("testTrickyQueues", new int[][]{
                        new int[0], // G
                        new int[]{0, 0, 0, 6}, // 1
                        new int[0], // 2
                        new int[0], // 3
                        new int[0], // 4
                        new int[]{6, 6, 0, 0, 0, 6}, // 5
                        new int[0] // 6
                }, 4, new int[]{0, 1, 5, 6, 5, 1, 0, 1, 0}),
                arguments("empty", new int[][]{
                        new int[0], // G
                        new int[0], // 1
                        new int[0], // 2
                }, 3, new int[]{0}),
                arguments("gabiFullLoad", new int[][]{
                        new int[0], // G
                        new int[0], // 1
                        new int[0], // 2
                        new int[0], // 3
                        new int[]{3}, // 4
                        new int[0], // 5
                        new int[]{1, 2, 3, 3, 1, 4, 3, 3, 3, 1, 3}, // 6
                }, 3, new int[]{0, 6, 4, 3, 2, 1, 6, 4, 3, 1, 6, 3, 6, 3, 1, 0}),
                arguments("testUpAndDown", new int[][]{
                        new int[]{3, 3, 3, 3, 3, 3}, // G
                        new int[]{}, // 1
                        new int[]{}, // 2
                        new int[]{}, // 3
                        new int[]{}, // 4
                        new int[]{4, 4, 4, 4, 4, 4}, // 5
                        new int[]{}, // 6
                }, 5, new int[]{0, 3, 5, 4, 0, 3, 5, 4, 0}),
                arguments("random3", new int[][]{
                        new int[]{2, 2}, // G
                        new int[]{}, // 1
                        new int[]{0, 1, 0, 0}, // 2
                }, 1, new int[]{0, 2, 0, 2, 1, 2, 0, 2, 0}),
                arguments("testLiftFullUp", new int[][]{
                        new int[]{3, 3, 3, 3, 3, 3}, // G
                        new int[]{}, // 1
                        new int[]{}, // 2
                        new int[]{}, // 3
                        new int[]{}, // 4
                        new int[]{}, // 5
                        new int[]{}, // 6
                }, 5, new int[]{0, 3, 0, 3, 0}),
                arguments("testHighlander", new int[][]{
                        new int[]{}, // G
                        new int[]{2}, // 1
                        new int[]{3, 3, 3}, // 2
                        new int[]{1}, // 3
                        new int[]{}, // 4
                        new int[]{}, // 5
                        new int[]{}, // 6
                }, 1, new int[]{0, 1, 2, 3, 1, 2, 3, 2, 3, 0}),
                arguments("testEnterOnGroundFloor", new int[][]{
                        new int[]{1, 2, 3, 4}, // G
                        new int[]{}, // 1
                        new int[]{}, // 2
                        new int[]{}, // 3
                        new int[]{}, // 4
                        new int[]{}, // 5
                        new int[]{}, // 6
                }, 5, new int[]{0, 1, 2, 3, 4, 0}),
                arguments("random4", new int[][]{
                        new int[]{4, 1, 2}, // G
                        new int[]{4}, // 1
                        new int[]{}, // 2
                        new int[]{7, 1}, // 3
                        new int[]{}, // 4
                        new int[]{7, 2, 7}, // 5
                        new int[]{7}, // 6
                        new int[]{0, 2, 1}, // 7
                        new int[]{2}, // 8
                        new int[]{4}, // 9
                }, 2, new int[]{0, 1, 3, 4, 5, 6, 7, 9, 8, 7, 5, 4, 3, 2, 1, 0, 2, 3, 6, 7, 5, 2, 0, 7, 5, 2, 1, 0}),
                arguments("random5", new int[][]{
                        new int[]{}, // G
                        new int[]{5, 8}, // 1
                        new int[]{6}, // 2
                        new int[]{8, 5}, // 3
                        new int[]{}, // 4
                        new int[]{6, 3}, // 5
                        new int[]{8, 7, 8, 0}, // 6
                        new int[]{6, 3, 4, 4}, // 7
                        new int[]{}, // 8
                }, 3, new int[]{0, 1, 2, 3, 5, 6, 7, 8, 7, 6, 5, 4, 3, 0, 3, 5, 6, 8, 7, 5, 4, 3, 0}),
                arguments("random6", new int[][]{
                        new int[]{}, // G
                        new int[]{5}, // 1
                        new int[]{1, 3, 3}, // 2
                        new int[]{4, 0, 0, 1}, // 3
                        new int[]{2}, // 4
                        new int[]{4, 1, 3}, // 5
                }, 1, new int[]{0, 1, 2, 3, 5, 4, 3, 2, 1, 2, 3, 4, 5, 3, 1, 2, 3, 5, 3, 0, 3, 0, 3, 1, 0}),
                arguments("random7", new int[][]{
                        new int[]{3, 6, 3, 6}, // G
                        new int[]{4, 2, 4}, // 1
                        new int[]{3, 1, 7}, // 2
                        new int[]{2, 7, 2, 8}, // 3
                        new int[]{2, 5, 3, 3}, // 4
                        new int[]{1, 4, 1, 3}, // 5
                        new int[]{}, // 6
                        new int[]{8, 5}, // 7
                        new int[]{}, // 8
                }, 2, new int[]{0, 1, 2, 3, 4, 6, 7, 8, 7, 5, 4, 3, 2, 1, 0, 1, 2, 3, 4, 6, 8, 5, 4, 3, 2, 1, 2, 3, 4,
                        5, 4, 3, 2, 1, 2, 4, 7, 0}),
                arguments("random8", new int[][]{
                        new int[]{}, // G
                        new int[]{0, 13}, // 1
                        new int[]{0, 8, 11}, // 2
                        new int[]{1, 4, 2}, // 3
                        new int[]{0}, // 4
                        new int[]{7, 7, 10}, // 5
                        new int[]{9}, // 6
                        new int[]{10, 9, 9}, // 7
                        new int[]{}, // 8
                        new int[]{}, // 9
                        new int[]{}, // 10
                        new int[]{1, 4}, // 11
                        new int[]{4}, // 12
                        new int[]{4, 2, 9, 12}, // 13
                        new int[]{5, 0, 6}, // 14
                }, 1, new int[]{0, 1, 2, 3, 5, 6, 7, 13, 14, 13, 12, 11, 5, 4, 3, 2, 1, 0, 2, 3, 5, 6, 7, 8, 14, 13, 12,
                        11, 3, 2, 1, 0, 2, 3, 5, 6, 7, 11, 14, 13, 12, 11, 6, 3, 2, 1, 0, 3, 4, 5, 6, 7, 10, 13, 12, 11,
                        4, 3, 2, 0, 5, 6, 7, 9, 13, 12, 11, 2, 5, 6, 7, 10, 13, 12, 11, 9, 6, 7, 9, 13, 12, 11, 4, 7, 9,
                        11, 1, 11, 4, 0})
        );
    }
}
