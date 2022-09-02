package com.example.java.challenge.misc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Unit test cases class for {@link RailFenceCipherChallenge}
 */
public class RailFenceCipherChallengeTest {

    @ParameterizedTest(name = "text: {0} - rail: {1}")
    @MethodSource("basicTestCases")
    void parameterizedBasicTestCases(final String text, final int rail, final String expected) {
        Assertions.assertEquals(expected, RailFenceCipherChallenge.encode(text, rail));
        Assertions.assertEquals(text, RailFenceCipherChallenge.decode(expected, rail));
    }

    /**
     * Generates basic tests cases values
     *
     * @return {@link Stream < Arguments >}
     */
    private static Stream<Arguments> basicTestCases() {
        return Stream.of(
                arguments("", 3, ""),
                arguments("Hello, World!", 3, "Hoo!el,Wrdl l"),
                arguments("Hello, World!", 4, "H !e,Wdloollr"),
                arguments("WEAREDISCOVEREDFLEEATONCE", 3, "WECRLTEERDSOEEFEAOCAIVDEN")
        );
    }
}
