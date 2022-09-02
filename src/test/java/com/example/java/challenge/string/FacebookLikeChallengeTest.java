package com.example.java.challenge.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Unit test cases class for {@link FacebookLikeChallenge}
 */
public class FacebookLikeChallengeTest {

    @ParameterizedTest(name = "whoArray: {0} - expected: {1}")
    @MethodSource("basicTestCases")
    void parameterizedBasicTestCases(final String[] whoArray, final String expected) {
        Assertions.assertEquals(expected, FacebookLikeChallenge.whoLikesIt(whoArray));
    }

    /**
     * Generates basic tests cases values
     *
     * @return {@link Stream < Arguments >}
     */
    private static Stream<Arguments> basicTestCases() {
        return Stream.of(
                arguments(new String[]{""}, "no one likes this"),
                arguments(new String[]{"Peter"}, "Peter likes this"),
                arguments(new String[]{"Jacob", "Alex"}, "Jacob and Alex like this"),
                arguments(new String[]{"Max", "John", "Mark"}, "Max, John and Mark like this"),
                arguments(new String[]{"Alex", "Jacob", "Mark", "Max"}, "Alex, Jacob and 2 others like this"),
                arguments(new String[]{"Alex", "Jacob", "Mark", "Max", "Susan"}, "Alex, Jacob and 3 others like this")
        );
    }
}