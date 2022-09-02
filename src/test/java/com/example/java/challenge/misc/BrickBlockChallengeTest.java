package com.example.java.challenge.misc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Unit test cases class for {@link BrickBlockChallenge}
 */
public class BrickBlockChallengeTest {

    @ParameterizedTest(name = "height: {0} - width: {1} - expected: {2}")
    @MethodSource("basicTestCases")
    void parameterizedBasicTestCases(final int height, final int width, final int expected) {
        Assertions.assertEquals(expected, BrickBlockChallenge.legoBlocks(height, width));
    }

    /**
     * Generates basic tests cases values
     *
     * @return {@link Stream < Arguments >}
     */
    private static Stream<Arguments> basicTestCases() {
        return Stream.of(
                arguments(1, 2, 1),
                arguments(1, 3, 1),
                arguments(1, 4, 1),
                arguments(2, 2, 3),
                arguments(4, 5, 35714),
                arguments(4, 6, 447902),
                arguments(4, 7, 5562914),
                arguments(694, 335, 30314890)
        );
    }
}
