package com.example.java.challenge.time;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Unit test cases class for {@link TimeConversionToMilitaryChallenge}
 */
public class TimeConversionToMilitaryChallengeTest {

    @ParameterizedTest(name = "text: {0} - expected: {1}")
    @MethodSource("basicTestCases")
    void parameterizedBasicTestCases(final String text, final String expected) {
        Assertions.assertEquals(expected, TimeConversionToMilitaryChallenge.convert(text));
    }

    /**
     * Generates basic tests cases values
     *
     * @return {@link Stream < Arguments >}
     */
    private static Stream<Arguments> basicTestCases() {
        return Stream.of(
                arguments("12:01:00PM", "12:01:00"),
                arguments("12:01:00AM", "00:01:00")
        );
    }
}
