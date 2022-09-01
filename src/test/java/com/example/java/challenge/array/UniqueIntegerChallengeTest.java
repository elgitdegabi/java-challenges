package com.example.java.challenge.array;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Unit test cases class for {@link UniqueIntegerChallenge}
 */
public class UniqueIntegerChallengeTest {

    @ParameterizedTest(name = "list: {0} - expected: {1}")
    @MethodSource("basicTestCases")
    void parameterizedBasicTestCases(final List<Integer> list, final int expected) {
        Assertions.assertEquals(expected, UniqueIntegerChallenge.find(list));
    }

    /**
     * Generates basic tests cases values
     *
     * @return {@link Stream < Arguments >}
     */
    private static Stream<Arguments> basicTestCases() {
        return Stream.of(
                arguments(List.of(1, 2, 3, 4, 3, 2, 1), 4),
                arguments(List.of(0, 0, 1, 2, 1), 2)
        );
    }
}
