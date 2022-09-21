package com.example.java.challenge.misc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Unit test cases class for {@link HandShakeProblemChallenge}
 */
public class HandShakeProblemChallengeTest {

    @ParameterizedTest(name = "handshakes: {0} - expected: {1}")
    @MethodSource("basicTestCases")
    void parameterizedBasicTestCases(final int handshakes, final int expected) {
        Assertions.assertEquals(expected, HandShakeProblemChallenge.getParticipants(handshakes));
    }

    /**
     * Generates basic tests cases values
     *
     * @return {@link Stream < Arguments >}
     */
    private static Stream<Arguments> basicTestCases() {
        return Stream.of(
                arguments(0, 0),
                arguments(1, 2),
                arguments(3, 3),
                arguments(6, 4),
                arguments(7, 5)
        );
    }
}
