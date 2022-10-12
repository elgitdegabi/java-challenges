package com.example.java.challenge.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Unit test cases class for {@link PalindromeWordChallenge}
 */
public class PalindromeWordChallengeTest {

    @ParameterizedTest(name = "text: {0} - expected: {1}")
    @MethodSource("basicTestCases")
    void parameterizedBasicTestCases(final String text, boolean expected) {
        Assertions.assertEquals(expected, PalindromeWordChallenge.isPalindrome(text));
    }

    /**
     * Generates basic tests cases values
     *
     * @return {@link Stream < Arguments >}
     */
    private static Stream<Arguments> basicTestCases() {
        return Stream.of(
                arguments("radar", true),
                arguments("raddar", true),
                arguments("Radar", false),
                arguments("RADar", false),
                arguments(getRandomPalindromeWord(5000), true),
                arguments("A" + getRandomPalindromeWord(1000), false)
        );
    }

    /**
     * Creates a random palindrome word based on given word length
     *
     * @param length word length
     * @return create palindrome word
     */
    private static String getRandomPalindromeWord(final int length) {
        char[] randomWord = new char[length];
        Random random = new Random();

        for (int i = 0; i < length / 2; i++) {
            randomWord[i] = (char) ('a' + random.nextInt(26));
            randomWord[length - 1 - i] = randomWord[i];
        }

        return String.valueOf(randomWord);
    }
}
