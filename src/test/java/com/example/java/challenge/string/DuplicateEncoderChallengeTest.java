package com.example.java.challenge.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Unit test cases class for {@link DuplicateEncoderChallenge}
 */
public class DuplicateEncoderChallengeTest {

    @ParameterizedTest(name = "text: {0} - expected: {1}")
    @MethodSource("basicTestCases")
    void parameterizedBasicTestCases(final String text, final String expected) {
        Assertions.assertEquals(expected, DuplicateEncoderChallenge.encode(text));
    }

    /**
     * Generates basic tests cases values
     *
     * @return {@link Stream < Arguments >}
     */
    private static Stream<Arguments> basicTestCases() {
        return Stream.of(
                arguments("Success", ")())())"),
                arguments("Prespecialized", ")()())()(()()("),
                arguments("W93", "((("),
                arguments("eie", ")()"),
                arguments("O49", "((("),
                arguments("<`lk", "(((("),
                arguments("31", "(("),
                arguments("Z8", "(("),
                arguments("5*{", "((("),
                arguments("   ()(   ", "))))())))")
        );
    }
}