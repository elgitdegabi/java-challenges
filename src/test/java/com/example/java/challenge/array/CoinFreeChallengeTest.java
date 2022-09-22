package com.example.java.challenge.array;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Unit test cases class for {@link CoinFreeChallenge}
 */
public class CoinFreeChallengeTest {

    @ParameterizedTest(name = "amount: {0} - coins: {1} - expected: {2}")
    @MethodSource("basicTestCases")
    void parameterizedBasicTestCases(final int amount, final int[] coins, final int expected) {
        Assertions.assertEquals(expected, CoinFreeChallenge.solve(amount, coins));
    }

    /**
     * Generates basic tests cases values
     *
     * @return {@link Stream < Arguments >}
     */
    private static Stream<Arguments> basicTestCases() {
        return Stream.of(
                arguments(75, new int[]{1, 5, 10, 25}, 3),
                arguments(123, new int[]{1, 5, 10, 25}, 9),
                arguments(75, new int[]{1, 2, 5, 10, 20, 50}, 3),
                arguments(123, new int[]{1, 2, 5, 10, 20, 50}, 5),
                arguments(352498558, new int[]{4, 1, 2}, 88124640),
                arguments(621930236, new int[]{2, 1, 16, 8, 144, 48}, 4318966),
                arguments(1517782740, new int[]{12, 3, 1, 48}, 31620476),
                arguments(1146781489, new int[]{4, 1}, 286695373),
                arguments(1008301027, new int[]{16, 8, 1, 4}, 63018817)
        );
    }
}
