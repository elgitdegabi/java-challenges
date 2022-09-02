package com.example.java.challenge.misc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Unit test cases class for {@link ChessBoardChallenge}
 */
public class ChessBoardChallengeTest {

    @ParameterizedTest(name = "rowSize: {0} - columnSize: {1} - expected: {2}")
    @MethodSource("basicTestCases")
    void parameterizedBasicTestCases(final int rowSize, final int columnSize, final String expected) {
        Assertions.assertEquals(expected, ChessBoardChallenge.createBoard(rowSize, columnSize));
    }

    /**
     * Generates basic tests cases values
     *
     * @return {@link Stream < Arguments >}
     */
    private static Stream<Arguments> basicTestCases() {
        return Stream.of(
                arguments(5, 5, "X X X\n" + " X X \n" + "X X X\n" + " X X \n" + "X X X\n")
        );
    }
}
