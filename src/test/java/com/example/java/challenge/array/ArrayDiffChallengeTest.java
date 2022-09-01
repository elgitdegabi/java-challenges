package com.example.java.challenge.array;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Unit test cases class for {@link ArrayDiffChallenge}
 */
public class ArrayDiffChallengeTest {

    @ParameterizedTest(name = "expected: {0} - array 1: {1} - array 2: {2}")
    @MethodSource("basicTestCases")
    void parameterizedBasicTestCases(final int[] expected, final int[] array1, final int[] array2) {
        Assertions.assertArrayEquals(expected, ArrayDiffChallenge.arrayDiff(array1, array2));
    }

    /**
     * Generates basic tests cases values
     *
     * @return {@link Stream < Arguments >}
     */
    private static Stream<Arguments> basicTestCases() {
        return Stream.of(
                arguments(new int[]{2}, new int[]{1, 2}, new int[]{1}),
                arguments(new int[]{2, 2}, new int[]{1, 2, 2}, new int[]{1}),
                arguments(new int[]{1}, new int[]{1, 2, 2}, new int[]{2}),
                arguments(new int[]{1}, new int[]{1, 2, 2}, new int[]{2}),
                arguments(new int[]{1, 2, 2}, new int[]{1, 2, 2}, new int[]{}),
                arguments(new int[]{}, new int[]{}, new int[]{1, 2})
        );
    }
}
