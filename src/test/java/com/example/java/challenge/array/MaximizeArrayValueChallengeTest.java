package com.example.java.challenge.array;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Unit test cases class for {@link MaximizeArrayValueChallenge}
 */
public class MaximizeArrayValueChallengeTest {

    @ParameterizedTest(name = "list: {0} - expected: {1}")
    @MethodSource("basicTestCases")
    void parameterizedBasicTestCases(final List<Integer> list, final int expected) {
        Assertions.assertEquals(expected, MaximizeArrayValueChallenge.minimizeMaximumValue(list));
    }

    /**
     * Generates basic tests cases values
     *
     * @return {@link Stream < Arguments >}
     */
    private static Stream<Arguments> basicTestCases() {
        return Stream.of(
                arguments(List.of(Integer.valueOf(3), Integer.valueOf(5), Integer.valueOf(15), Integer.valueOf(19)), 12),
                arguments(List.of(Integer.valueOf(1), Integer.valueOf(5), Integer.valueOf(7), Integer.valueOf(6)), 5)
        );
    }
}
