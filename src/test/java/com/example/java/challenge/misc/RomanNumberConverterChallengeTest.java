package com.example.java.challenge.misc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Unit test cases class for {@link RomanNumberConverterChallenge}
 */
public class RomanNumberConverterChallengeTest {

    @ParameterizedTest(name = "number: {0} - roman: {1}")
    @MethodSource("basicTestCases")
    void parameterizedBasicTestCases(final int number, final String expectedToRoman, final int expectedFromRoman) {
        Assertions.assertEquals(expectedToRoman, RomanNumberConverterChallenge.RomanNumberConverter.toRoman(number));
        Assertions.assertEquals(expectedFromRoman, RomanNumberConverterChallenge.RomanNumberConverter.fromRoman(expectedToRoman));
    }

    /**
     * Generates basic tests cases values
     *
     * @return {@link Stream < Arguments >}
     */
    private static Stream<Arguments> basicTestCases() {
        return Stream.of(
                arguments(0, "", 0),
                arguments(4000, "", 0),
                arguments(1, "I", 1),
                arguments(2, "II", 2),
                arguments(1000, "M", 1000),
                arguments(1990, "MCMXC", 1990),
                arguments(2008, "MMVIII", 2008),
                arguments(1666, "MDCLXVI", 1666)
        );
    }
}
