package com.example.java.challenge.array;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Unit test cases class for {@link MinimaxSumChallenge}
 */
public class MinimaxSumChallengeTest {

    @ParameterizedTest(name = "list: {0} - expected: {1}")
    @MethodSource("basicTestCases")
    void parameterizedBasicTestCases(final List<Integer> list, final String expected) {
        Assertions.assertEquals(expected, MinimaxSumChallenge.calculate(list));
    }

    /**
     * Generates basic tests cases values
     *
     * @return {@link Stream < Arguments >}
     */
    private static Stream<Arguments> basicTestCases() {
        return Stream.of(
                arguments(List.of(1, 2, 3, 4, 5), "10 14"),
                arguments(List.of(7, 69, 2, 221, 8974), "299 9271"),
                arguments(List.of(256741038, 623958417, 467905213, 714532089, 938071625), "2063136757 2744467344")
        );
    }
}
