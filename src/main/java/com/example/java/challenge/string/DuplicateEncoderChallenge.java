package com.example.java.challenge.string;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The goal of this exercise is to convert a string to a new string where each character in the new string is "("
 * if that character appears only once in the original string, or ")" if that character appears more than once in the
 * original string. Ignore capitalization when determining if a character is a duplicate.
 * For example:
 * Case 1: "Success" -> ")())())"
 */
@Slf4j
public class DuplicateEncoderChallenge {

    /**
     * Encodes given word
     *
     * @param word {@link String}
     * @return {@link String} encoded word
     */
    public static String encode(final String word) {
        List<Character> text = word.toLowerCase().chars().mapToObj(e -> (char) e).collect(Collectors.toList());
        StringBuffer result = new StringBuffer();

        for (int i = 0; i < text.size(); i++) {
            Character character = text.get(i);

            if (text.stream().filter(c -> c.equals(character)).count() > 1) {
                result.append(")");
            } else {
                result.append("(");
            }
        }

        return result.toString();
    }
}
