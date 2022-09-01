package com.example.java.challenge.array;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Unit test cases class for {@link MissingLetterChallenge}
 */
public class MissingLetterChallengeTest {

    @ParameterizedTest(name = "text: {0} - expected: {1}")
    @MethodSource("basicTestCases")
    void parameterizedBasicTestCases(final char[] text, final char expected) {
        Assertions.assertEquals(expected, MissingLetterChallenge.findMissingLetter(text));
    }

    /**
     * Generates basic tests cases values
     *
     * @return {@link Stream < Arguments >}
     */
    private static Stream<Arguments> basicTestCases() {
        return Stream.of(
                arguments(new char[]{'a', 'b', 'c', 'd', 'f'}, 'e'),
                arguments(new char[]{'O', 'Q', 'R', 'S'}, 'P')
        );
    }
}
