package com.example.java.challenge.misc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Unit test cases class for {@link PrintDiamondStringChallenge}
 */
public class PrintDiamondStringChallengeTest {

    @ParameterizedTest(name = "number: {0}")
    @MethodSource("basicTestCases")
    void parameterizedBasicTestCases(final int number, final String expected) {
        Assertions.assertEquals(expected, PrintDiamondStringChallenge.print(number));
    }

    /**
     * Generates basic tests cases values
     *
     * @return {@link Stream < Arguments >}
     */
    private static Stream<Arguments> basicTestCases() {
        return Stream.of(
                arguments(2, null),
                arguments(-2, null),
                arguments(0, null),
                arguments(1, "*\n"),
                arguments(5, "  *\n ***\n*****\n ***\n  *\n"),
                arguments(3, " *\n***\n *\n"),
                arguments(15, "       *\n      ***\n     *****\n    *******\n   *********\n  ***********\n " +
                        "*************\n***************\n *************\n  ***********\n   *********\n    *******\n" +
                        "     *****\n      ***\n       *\n")
        );
    }
}
