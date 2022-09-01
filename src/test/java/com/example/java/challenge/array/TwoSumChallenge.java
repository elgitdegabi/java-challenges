package com.example.java.challenge.array;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Write a function that takes an array of integers and a target number.
 * It should find two different items in the array that, when added together, give the target value.
 * The indices of these items should then be returned in a tuple / list (depending on your language) like so: (index1, index2).
 * For example:
 * Case 1: {1234, 5678, 9012} & 14690 -> {1, 2}
 */
@Slf4j
public class TwoSumChallenge {

    /**
     * TwoSum class
     */
    public static class TwoSum {

        /**
         * Calculates indexes of array sum values equals to given target
         * @param numbers int[]
         * @param target int
         * @return int[] indexes
         */
        public static int[] twoSum(final int[] numbers, final int target) {
            for (int i = 0; i < numbers.length; i++) {
                for (int j = i+1; j < numbers.length; j++) {
                    if (target == numbers[i] + numbers[j]) {
                        return new int[] {i, j};
                    }
                }
            }

            return null;
        }
    }

    /**
     * Unit test cases class
     */
    public static class MissingLetterTest {
        @ParameterizedTest(name = "numbers: {0}, target: {1}, expected: {2}")
        @MethodSource("basicTestCases")
        @DisplayName("Basic tests")
        void parameterizedTestCase1(int[] numbers, int target, int[] expected) {
            int[] result = TwoSum.twoSum(numbers.clone(), target);

            Assertions.assertNotNull(result, "Should return an array");
            Assertions.assertEquals(expected.length, result.length, "Returned array must be of length 2");
            Assertions.assertNotEquals(result[0], result[1], "Indices must be distinct");
            Assertions.assertEquals(
                    target,
                    numbers[result[0]] + numbers[result[1]],
                    String.format("Numbers %d, %d at positions %d, %d do not add up to target", numbers[result[0]], numbers[result[1]], result[0], result[1]));
        }

        /**
         * Generates basic tests cases values
         * @return {@link Stream<Arguments>}
         */
        private static Stream<Arguments> basicTestCases() {
            return Stream.of(
                    arguments(new int[]{1, 2, 3}, 4, new int[]{0, 2}),
                    arguments(new int[]{1234, 5678, 9012}, 14690, new int[]{1, 2}),
                    arguments(new int[]{2, 2, 3}, 4, new int[]{0, 1}),
                    arguments(new int[]{2, 3, 1}, 4, new int[]{1, 2})
            );
        }
    }
}
