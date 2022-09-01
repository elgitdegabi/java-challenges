package com.example.java.challenge.array;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Unit test cases class for {@link MinimumBribesChallenge}
 */
public class MinimumBribesChallengeTest {

    @ParameterizedTest(name = "list: {0} - expected: {1}")
    @MethodSource("basicTestCases")
    void parameterizedBasicTestCases(final List<Integer> list, final String expected) {
        Assertions.assertEquals(expected, MinimumBribesChallenge.minimumBribes(list));
    }

    /**
     * Generates basic tests cases values
     *
     * @return {@link Stream < Arguments >}
     */
    private static Stream<Arguments> basicTestCases() {
        return Stream.of(
                arguments(List.of(2, 1, 5, 3, 4), "3"),
                arguments(List.of(2, 5, 1, 3, 4), "Too chaotic"),
                arguments(List.of(5, 1, 2, 3, 7, 8, 6, 4), "Too chaotic"),
                arguments(List.of(1, 2, 5, 3, 7, 8, 6, 4), "7"),
                arguments(List.of(1, 2, 5, 3, 4, 7, 8, 6), "4")
        );
    }
}
