package com.example.java.challenge.array;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Unit test cases class for {@link ZigZagChallenge}
 */
public class ZigZagChallengeTest {

    @ParameterizedTest(name = "array: {0} - size: {1} - expected: {2}")
    @MethodSource("basicTestCases")
    void parameterizedBasicTestCases(final int[] array, final int size, final String expected) {
        Assertions.assertEquals(expected, ZigZagChallenge.findZigZagSequence(array, size));
    }

    /**
     * Generates basic tests cases values
     *
     * @return {@link Stream < Arguments >}
     */
    private static Stream<Arguments> basicTestCases() {
        return Stream.of(
                arguments(new int[]{1, 4, 5, 3, 2}, 5, "1 2 5 4 3 "),
                arguments(new int[]{1, 2, 3, 4, 5, 6, 7}, 7, "1 2 3 7 6 5 4 ")
        );
    }
}
