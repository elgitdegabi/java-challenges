package com.example.java.challenge.array;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Unit test cases class for {@link TowerBreakersChallenge}
 */
public class TowerBreakersChallengeTest {

    @ParameterizedTest(name = "towers: {0} - size: {1} - winner: {2}")
    @MethodSource("basicTestCases")
    void parameterizedBasicTestCases(final int towers, final int size, final int expected) {
        Assertions.assertEquals(expected, TowerBreakersChallenge.towerBreakers(towers, size));
    }

    /**
     * Generates basic tests cases values
     *
     * @return {@link Stream < Arguments >}
     */
    private static Stream<Arguments> basicTestCases() {
        return Stream.of(
                arguments(2, 6, 2),
                arguments(2, 2, 2),
                arguments(1, 4, 1)
        );
    }
}
