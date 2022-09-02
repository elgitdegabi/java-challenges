package com.example.java.challenge.matrix;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Unit test cases class for {@link GridSquareChallenge}
 */
public class GridSquareChallengeTest {

    @ParameterizedTest(name = "grid: {0} - expected: {1}")
    @MethodSource("basicTestCases")
    void parameterizedBasicTestCases(final List<String> grid, final String expected) {
        Assertions.assertEquals(expected, GridSquareChallenge.isInAscendingAlphabeticalOrder(grid));
    }

    /**
     * Generates basic tests cases values
     *
     * @return {@link Stream < Arguments >}
     */
    private static Stream<Arguments> basicTestCases() {
        return Stream.of(
                arguments(List.of("abc", "lmp", "qrt"), "YES"),
                arguments(List.of("mpxz", "abcd", "wlmf"), "NO"),
                arguments(List.of("abc", "hjk", "mpq", "rtv"), "YES")
        );
    }
}
