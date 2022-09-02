package com.example.java.challenge.structure;

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
 * Unit test cases class for {@link QueueTwoStacksChallenge}
 */
@ExtendWith(OutputCaptureExtension.class) //JUnit 5 to test System.out & System.error
public class QueueTwoStacksChallengeTest {

    @ParameterizedTest(name = "list: {0} - expected: {1}")
    @MethodSource("basicTestCases")
    void parameterizedBasicTestCases(final List<Integer> list, final String expected, final CapturedOutput output) {
        QueueTwoStacksChallenge.execute(list);
        Assertions.assertEquals(expected, output.getOut().replaceAll("\r\n", " "));
    }

    /**
     * Generates basic tests cases values
     *
     * @return {@link Stream < Arguments >}
     */
    private static Stream<Arguments> basicTestCases() {
        return Stream.of(
                arguments(List.of(1, 42, 2, 1, 14, 3, 1, 15, 1, 16, 3, 2, 3, 2, 3), "14 14 15 16 ")
        );
    }
}
