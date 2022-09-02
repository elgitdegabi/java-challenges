package com.example.java.challenge.misc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Unit test cases class for {@link BalancedParenthesesChallenge}
 */
public class BalancedParenthesesChallengeTest {

    @ParameterizedTest(name = "text: {0}")
    @MethodSource("basicTestCases")
    void parameterizedBasicTestCases(final String text, final boolean expected) {
        Assertions.assertEquals(expected, BalancedParenthesesChallenge.validParentheses(text));
    }

    /**
     * Generates basic tests cases values
     *
     * @return {@link Stream < Arguments >}
     */
    private static Stream<Arguments> basicTestCases() {
        return Stream.of(
                arguments("()", true),
                arguments("())", false),
                arguments("32423(sgsdg)", true),
                arguments("(dsgdsg))2432", false),
                arguments("adasdasfa", true)
        );
    }
}
