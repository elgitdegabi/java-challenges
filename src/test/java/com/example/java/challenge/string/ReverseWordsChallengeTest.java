package com.example.java.challenge.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Unit test cases class for {@link ReverseWordsChallenge}
 */
public class ReverseWordsChallengeTest {

    @ParameterizedTest(name = "text: {0} - expected: {1}")
    @MethodSource("basicTestCases")
    void parameterizedBasicTestCases(final String text, final String expected) {
        Assertions.assertEquals(expected, ReverseWordsChallenge.reverseWords(text));
    }

    /**
     * Generates basic tests cases values
     *
     * @return {@link Stream < Arguments >}
     */
    private static Stream<Arguments> basicTestCases() {
        return Stream.of(
                arguments("ehT kciuq nworb xof spmuj revo eht yzal .god", "The quick brown fox jumps over the lazy dog."),
                arguments("", ""),
                arguments("elppa", "apple"),
                arguments("a b c d", "a b c d"),
                arguments("elbuod  decaps  sdrow", "double  spaced  words"),
                arguments("   ", "   ")
        );
    }
}
