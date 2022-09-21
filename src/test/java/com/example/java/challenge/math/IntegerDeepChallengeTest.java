package com.example.java.challenge.math;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Unit test cases class for {@link IntegerDeepChallenge}
 */
public class IntegerDeepChallengeTest {

    @ParameterizedTest(name = "number: {0} - expected: {1}")
    @MethodSource("basicTestCases")
    void parameterizedBasicTestCases(final int number, final int expected) {
        Assertions.assertEquals(expected, IntegerDeepChallenge.computeDepth(number));
    }

    /**
     * Generates basic tests cases values
     *
     * @return {@link Stream < Arguments >}
     */
    private static Stream<Arguments> basicTestCases() {
        return Stream.of(
                arguments(1, 10),
                arguments(42, 9)
        );
    }
}
