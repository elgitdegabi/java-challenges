package com.example.java.challenge.math;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Unit test cases class for {@link DigPowChallenge}
 */
public class DigPowChallengeTest {

    @ParameterizedTest(name = "number: {0} - power: {1} - expected: {2}")
    @MethodSource("basicTestCases")
    void parameterizedBasicTestCases(final int number, final int power, final long expected) {
        Assertions.assertEquals(expected, DigPowChallenge.digPow(number, power));
    }

    /**
     * Generates basic tests cases values
     *
     * @return {@link Stream < Arguments >}
     */
    private static Stream<Arguments> basicTestCases() {
        return Stream.of(
                arguments(89, 1, 1),
                arguments(92, 1, -1),
                arguments(46288, 3, 51),
                arguments(695, 2, 2),
                arguments(699, 2, -1)
        );
    }
}
