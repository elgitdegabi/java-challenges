package com.example.java.challenge.math;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Unit test cases class for {@link FindBlockNumberChallenge}
 */
public class FindBlockNumberChallengeTest {

    @ParameterizedTest(name = "number: {0}")
    @MethodSource("basicTestCases")
    void parameterizedBasicTestCases(final long number, final long expected) {
        Assertions.assertEquals(expected, FindBlockNumberChallenge.findNumberOfBlocks(number));
    }

    /**
     * Generates basic tests cases values
     *
     * @return {@link Stream < Arguments >}
     */
    private static Stream<Arguments> basicTestCases() {
        return Stream.of(
                arguments(4183059834009L, 2022),
                arguments(24723578342962L, -1),
                arguments(135440716410000L, 4824),
                arguments(40539911473216L, 3568),
                arguments(1853274121997012100L, 52179)
        );
    }
}
