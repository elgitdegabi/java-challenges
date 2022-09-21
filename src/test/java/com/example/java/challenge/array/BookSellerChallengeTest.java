package com.example.java.challenge.array;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Unit test cases class for {@link BookSellerChallenge}
 */
public class BookSellerChallengeTest {

    @ParameterizedTest(name = "stock: {0} - categories: {1} - expected: {2}")
    @MethodSource("basicTestCases")
    void parameterizedBasicTestCases(final String[] stock, final String[] categories, final String expected) {
        Assertions.assertEquals(expected, BookSellerChallenge.stockSummary(stock, categories));
    }

    /**
     * Generates basic tests cases values
     *
     * @return {@link Stream < Arguments >}
     */
    private static Stream<Arguments> basicTestCases() {
        return Stream.of(
                arguments(
                        new String[]{"ABAR 200", "CDXE 500", "BKWR 250", "BTSQ 890", "DRTY 600"},
                        new String[]{"A", "B"},
                        "(A : 200) - (B : 1140)"),
                arguments(
                        new String[]{},
                        new String[]{"A", "B"},
                        "")
        );
    }
}
