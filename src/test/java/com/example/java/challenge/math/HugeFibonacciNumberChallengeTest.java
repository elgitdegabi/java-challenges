package com.example.java.challenge.math;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigInteger;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Unit test cases class for {@link HugeFibonacciNumberChallenge}
 */
public class HugeFibonacciNumberChallengeTest {

    @ParameterizedTest(name = "number: {0} - expected: {1}")
    @MethodSource("basicTestCases")
    void parameterizedBasicTestCases(final BigInteger number, final BigInteger expected) {
        Assertions.assertEquals(expected, HugeFibonacciNumberChallenge.fib(number));
    }

    /**
     * Generates basic tests cases values
     *
     * @return {@link Stream < Arguments >}
     */
    private static Stream<Arguments> basicTestCases() {
        return Stream.of(
                arguments(BigInteger.ZERO, BigInteger.ZERO),
                arguments(BigInteger.ONE, BigInteger.ONE),
                arguments(BigInteger.valueOf(2), BigInteger.ONE),
                arguments(BigInteger.valueOf(3), BigInteger.valueOf(2)),
                arguments(BigInteger.valueOf(4), BigInteger.valueOf(3)),
                arguments(BigInteger.valueOf(5), BigInteger.valueOf(5)),
                arguments(BigInteger.valueOf(-15), BigInteger.valueOf(610)),
                arguments(BigInteger.valueOf(-5), BigInteger.valueOf(5)),
                arguments(BigInteger.valueOf(-13), BigInteger.valueOf(233)),
                arguments(BigInteger.valueOf(-48), BigInteger.valueOf(-4807526976L)),
                arguments(BigInteger.valueOf(-1), BigInteger.valueOf(-1)),
                arguments(BigInteger.valueOf(-6), BigInteger.valueOf(-8))
        );
    }
}
