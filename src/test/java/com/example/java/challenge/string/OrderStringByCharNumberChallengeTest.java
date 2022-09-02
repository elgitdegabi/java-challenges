package com.example.java.challenge.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Unit test cases class for {@link OrderStringByCharNumberChallenge}
 */
public class OrderStringByCharNumberChallengeTest {

    @ParameterizedTest(name = "word: {0}")
    @MethodSource("basicTestCases")
    void parameterizedBasicTestCases(final String words, final String expected) {
        Assertions.assertEquals(expected, OrderStringByCharNumberChallenge.order(words));
    }

    /**
     * Generates basic tests cases values
     *
     * @return {@link Stream < Arguments >}
     */
    private static Stream<Arguments> basicTestCases() {
        return Stream.of(
                arguments("is2 Thi1s T4est 3a", "Thi1s is2 3a T4est"),
                arguments("", ""),
                arguments("4of Fo1r pe6ople g3ood th5e the2", "Fo1r the2 g3ood 4of th5e pe6ople")
        );
    }
}
