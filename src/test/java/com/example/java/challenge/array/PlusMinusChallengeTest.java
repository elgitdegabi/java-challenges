package com.example.java.challenge.array;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Unit test cases class for {@link PlusMinusChallenge}
 */
public class PlusMinusChallengeTest {

    @ParameterizedTest(name = "list: {0} - expected: {1}")
    @MethodSource("basicTestCases")
    void parameterizedBasicTestCases(final List<Integer> list, final String expected) {
        Assertions.assertEquals(expected, PlusMinusChallenge.calculate(list));
    }

    /**
     * Generates basic tests cases values
     *
     * @return {@link Stream < Arguments >}
     */
    private static Stream<Arguments> basicTestCases() {
        return Stream.of(
                arguments(List.of(-4, 3, -9, 0, 4, 1), "0.50000 0.33333 0.16667"),
                arguments(List.of(1, 2, 3, -1, -2, -3, 0, 0), "0.37500 0.37500 0.25000")
        );
    }
}
