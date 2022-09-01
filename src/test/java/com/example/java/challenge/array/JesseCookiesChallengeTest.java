package com.example.java.challenge.array;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Unit test cases class for {@link JesseCookiesChallenge}
 */
public class JesseCookiesChallengeTest {

    @ParameterizedTest(name = "sweetness: {0} - list: {1} - expected: {2}")
    @MethodSource("basicTestCases")
    void parameterizedBasicTestCases(final int sweetness, List<Integer> list, final int expected) {
        Assertions.assertEquals(expected, JesseCookiesChallenge.execute(sweetness, list));
    }

    /**
     * Generates basic tests cases values
     *
     * @return {@link Stream < Arguments >}
     */
    private static Stream<Arguments> basicTestCases() {
        return Stream.of(
                arguments(9, List.of(2, 7, 3, 6, 4, 6), 4),
                arguments(7, List.of(1, 2, 3, 9, 10, 12), 2),
                arguments(1000, List.of(52, 96, 13, 37), -1),
                arguments(105823341, List.of(52, 96, 13, 37), -1)
        );
    }
}
