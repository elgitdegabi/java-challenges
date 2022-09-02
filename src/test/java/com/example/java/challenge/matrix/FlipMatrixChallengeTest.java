package com.example.java.challenge.matrix;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Unit test cases class for {@link FlipMatrixChallenge}
 */
public class FlipMatrixChallengeTest {

    @ParameterizedTest(name = "list: {0} - expected: {1}")
    @MethodSource("basicTestCases")
    void parameterizedBasicTestCases(final List<List<Integer>> list, final int expected) {
        Assertions.assertEquals(expected, FlipMatrixChallenge.calculate(list));
    }

    /**
     * Generates basic tests cases values
     *
     * @return {@link Stream < Arguments >}
     */
    private static Stream<Arguments> basicTestCases() {
        return Stream.of(
                arguments(List.of(List.of(112, 42, 83, 119), List.of(56, 125, 56, 49), List.of(15, 78, 101, 43), List.of(62, 98, 114, 108)), 414),
                arguments(List.of(List.of(107, 54, 128, 15), List.of(12, 75, 110, 138), List.of(100, 96, 34, 85), List.of(75, 15, 28, 112)), 488)
        );
    }
}
