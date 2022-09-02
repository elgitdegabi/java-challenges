package com.example.java.challenge.misc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Unit test cases class for {@link CesarCipherChallenge}
 */
public class CesarCipherChallengeTest {

    @ParameterizedTest(name = "text: {0} - size: {1} - expected: {2}")
    @MethodSource("basicTestCases")
    void parameterizedBasicTestCases(final String text, final int size, final String expected) {
        Assertions.assertEquals(expected, CesarCipherChallenge.calculate(text, size));
    }

    /**
     * Generates basic tests cases values
     *
     * @return {@link Stream < Arguments >}
     */
    private static Stream<Arguments> basicTestCases() {
        return Stream.of(
                arguments("middle-Outz", 2, "okffng-Qwvb"),
                arguments("Always-Look-on-the-Bright-Side-of-Life", 5, "Fqbfdx-Qttp-ts-ymj-Gwnlmy-Xnij-tk-Qnkj"),
                arguments("Pz-/aI/J`EvfthGH", 66, "Dn-/oW/X`SjthvUV")
        );
    }
}
