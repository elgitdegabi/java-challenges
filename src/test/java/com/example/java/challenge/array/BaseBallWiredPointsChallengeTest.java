package com.example.java.challenge.array;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Unit test cases class for {@link BaseBallWiredPointsChallenge}
 */
public class BaseBallWiredPointsChallengeTest {

    @ParameterizedTest(name = "ops: {0} - expected: {1}")
    @MethodSource("basicTestCases")
    void parameterizedBasicTestCases(final String[] operations, final int expected) {
        Assertions.assertEquals(expected, BaseBallWiredPointsChallenge.calPoints(operations));
    }

    /**
     * Generates basic tests cases values
     *
     * @return {@link Stream < Arguments >}
     */
    private static Stream<Arguments> basicTestCases() {
        return Stream.of(
                arguments(new String[]{}, 0),
                arguments(new String[]{"5"}, 5),
                arguments(new String[]{"C", "D", "+"}, 0),
                arguments(new String[]{"5", "2", "C", "D", "+"}, 30),
                arguments(new String[]{"5", "-2", "4", "C", "D", "9", "+", "+"}, 27)
        );
    }
}
