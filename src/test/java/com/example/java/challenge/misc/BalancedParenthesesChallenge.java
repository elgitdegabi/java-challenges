package com.example.java.challenge.misc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Stack;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Determines if the order of the parentheses is valid. The function should return true if the string is valid, and false if it's invalid.
 * Along with opening (() and closing ()) parenthesis, input may contain any valid ASCII characters.
 * Furthermore, the input string may be empty and/or not contain any parentheses at all.
 * Do not treat other forms of brackets as parentheses (e.g. [], {}, <>).
 * For example:
 * Case 1: "32423(sgsdg)" -> true
 */
@Slf4j
public class BalancedParenthesesChallenge {

    /**
     * BalancedParentheses class
     */
    public static class BalancedParentheses {

        /**
         * Validates parentheses balance from given text
         * @param parens {@link String} text
         * @return boolean value if parentheses are balanced or not
         */
        public static boolean validParentheses(String parens) {
            Stack<Character> openStack = new Stack<>();

            for (int i = 0; i < parens.length(); i++) {
                if ('(' == parens.charAt(i)) {
                    openStack.push(parens.charAt(i));
                } else if (')' == parens.charAt(i)) {
                    if (openStack.size() < 1 || openStack.peek().charValue() != '(') {
                        return false;
                    } else {
                        openStack.pop();
                    }
                }
            }

            return (openStack.size() < 1? true: false);
        }
    }

    /**
     * Unit test cases class
     */
    public static class CreateSpiralTest {
        @ParameterizedTest(name = "text: {0}")
        @MethodSource("basicTestCases")
        void parameterizedTestCase1(final String text, final boolean expected) {
            Assertions.assertEquals(expected, BalancedParentheses.validParentheses(text));
        }

        /**
         * Generates basic tests cases values
         *
         * @return {@link Stream < Arguments >}
         */
        private static Stream<Arguments> basicTestCases() {
            return Stream.of(
                    arguments("()", true),
                    arguments("())", false),
                    arguments("32423(sgsdg)", true),
                    arguments("(dsgdsg))2432", false),
                    arguments("adasdasfa", true)
            );
        }
    }
}
