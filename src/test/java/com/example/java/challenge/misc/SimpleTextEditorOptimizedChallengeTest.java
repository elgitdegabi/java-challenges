package com.example.java.challenge.misc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Unit test cases class for {@link SimpleTextEditorOptimizedChallenge}
 */
@ExtendWith(OutputCaptureExtension.class) //JUnit 5 to test System.out & System.error
public class SimpleTextEditorOptimizedChallengeTest {

    @ParameterizedTest(name = "text: {0} - expected: {1}")
    @MethodSource("basicTestCases")
    void parameterizedBasicTestCases(final List<String> text, final String expected, final CapturedOutput output) {
        text.stream().forEach(t -> SimpleTextEditorOptimizedChallenge.execute(t));
        Assertions.assertEquals(expected, output.getOut().replaceAll("\r\n", " "));
    }

    /**
     * Generates basic tests cases values
     *
     * @return {@link Stream < Arguments >}
     */
    private static Stream<Arguments> basicTestCases() {
        return Stream.of(
                arguments(List.of("1 abc", "3 3", "2 3", "1 xy", "3 2", "4", "4", "3 1"), "c y a ")
        );
    }
}
