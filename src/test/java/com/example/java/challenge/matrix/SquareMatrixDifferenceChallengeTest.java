package com.example.java.challenge.matrix;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Unit test cases class for {@link SquareMatrixDifferenceChallenge}
 */
public class SquareMatrixDifferenceChallengeTest {

    @ParameterizedTest(name = "list: {0} - expected: {1}")
    @MethodSource("basicTestCases")
    void parameterizedBasicTestCases(final List<List<Integer>> list, final int expected) {
        Assertions.assertEquals(expected, SquareMatrixDifferenceChallenge.calculate(list));
    }

    /**
     * Generates basic tests cases values
     *
     * @return {@link Stream < Arguments >}
     */
    private static Stream<Arguments> basicTestCases() {
        return Stream.of(
                arguments(List.of(List.of(1, 2, 3), List.of(4, 5, 6), List.of(9, 8, 9)), 2),
                arguments(List.of(List.of(11, 2, 4), List.of(4, 5, 6), List.of(10, 8, -12)), 15)
        );
    }
}
