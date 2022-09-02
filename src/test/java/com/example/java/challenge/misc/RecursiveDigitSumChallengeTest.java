package com.example.java.challenge.misc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Unit test cases class for {@link RecursiveDigitSumChallenge}
 */
public class RecursiveDigitSumChallengeTest {

    @ParameterizedTest(name = "text: {0} - times: {1} - expected: {2}")
    @MethodSource("basicTestCases")
    void parameterizedBasicTestCases(final String text, final int times, final int expected) {
        Assertions.assertEquals(expected, RecursiveDigitSumChallenge.superDigit(text, times));
    }

    /**
     * Generates basic tests cases values
     *
     * @return {@link Stream < Arguments >}
     */
    private static Stream<Arguments> basicTestCases() {
        return Stream.of(
                arguments("148", 3, 3),
                arguments("9875", 4, 8),
                arguments("123", 3, 9)
        );
    }
}
