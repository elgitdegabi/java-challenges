package com.example.java.challenge.misc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Unit test cases class for {@link BalancedBracketsChallenge}
 */
public class BalancedBracketsChallengeTest {

    @ParameterizedTest(name = "text: {0} - expected: {1}")
    @MethodSource("basicTestCases")
    void parameterizedBasicTestCases(final String text, final String expected) {
        Assertions.assertEquals(expected, BalancedBracketsChallenge.isBalanced(text));
    }

    /**
     * Generates basic tests cases values
     *
     * @return {@link Stream < Arguments >}
     */
    private static Stream<Arguments> basicTestCases() {
        return Stream.of(
                arguments("{[()]}", "YES"),
                arguments("{[(])}", "NO"),
                arguments("{{[[(())]]}}", "YES"),
                arguments("{{([])}}", "YES"),
                arguments("{{)[](}}", "NO"),
                arguments("{(([])[])[]}", "YES"),
                arguments("{(([])[])[]]}", "NO"),
                arguments("{(([])[])[]}[]", "YES")
        );
    }
}
