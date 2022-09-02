package com.example.java.challenge.matrix;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Unit test cases class for {@link KeyPadChallenge}
 */
public class KeyPadChallengeTest {

    @ParameterizedTest(name = "text: {0} - keypad: {1} - expected: {2}")
    @MethodSource("basicTestCases")
    void parameterizedBasicTestCases(final String text, final String keypad, final int expected) {
        Assertions.assertEquals(expected, KeyPadChallenge.calculateTimeInSeconds(text, keypad));
    }

    /**
     * Generates basic tests cases values
     *
     * @return {@link Stream < Arguments >}
     */
    private static Stream<Arguments> basicTestCases() {
        return Stream.of(
                arguments("423692", "923857614", 8)
        );
    }
}
