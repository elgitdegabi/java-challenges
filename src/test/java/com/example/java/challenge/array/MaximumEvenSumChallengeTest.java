package com.example.java.challenge.array;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Unit test cases class for {@link MaximumEvenSumChallenge}
 */
public class MaximumEvenSumChallengeTest {

    @ParameterizedTest(name = "list: {0} - expected: {1}")
    @MethodSource("basicTestCases")
    void parameterizedBasicTestCases(final List<Integer> list, final int expected) {
        Assertions.assertEquals(expected, MaximumEvenSumChallenge.getMaximumEvenSum(list));
    }

    /**
     * Generates basic tests cases values
     *
     * @return {@link Stream < Arguments >}
     */
    private static Stream<Arguments> basicTestCases() {
        return Stream.of(
                arguments(List.of(6, 3, 4, -1, 9, 17), 38),
                arguments(List.of(-1, -2, -3, 8, 7), 14),
                arguments(List.of(2, 3, 6, -5, 10, 1, 1), 22),
                arguments(List.of(2, 3, 6, 4, 10, 1, 1), 26),
                arguments(List.of(-2, -3, -6, -4, -10, -1, -1), -2),
                arguments(List.of(-1, -1, -1, -2), -2),
                arguments(List.of(1, 2), 2)
        );
    }
}
