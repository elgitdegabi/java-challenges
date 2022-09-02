package com.example.java.challenge.misc;

import lombok.extern.slf4j.Slf4j;

/**
 * Julius Caesar protected his confidential information by encrypting it using a cipher.
 * Caesar's cipher shifts each letter by a number of letters. If the shift takes you past the end of the alphabet,
 * just rotate back to the front of the alphabet.
 * For example:
 * Case 1: middle-Outz; 2 -> okffng-Qwvb
 * Case 2: Always-Look-on-the-Bright-Side-of-Life; 5 -> Fqbfdx-Qttp-ts-ymj-Gwnlmy-Xnij-tk-Qnkj
 * Case 3: Pz-/aI/J`EvfthGH; 66 ->  Dn-/oW/X`SjthvUV
 */
@Slf4j
public class CesarCipherChallenge {

    /**
     * Calculate cipher from given text and shift size
     *
     * @param s {@link String}
     * @param k shift size
     * @return {@link String} cipher
     */
    public static String calculate(final String s, final int k) {
        char[] text = new char[s.length()];

        for (int i = 0; i < text.length; i++) {
            text[i] = calculateCharWithShift(s.charAt(i), k);
        }
        return String.valueOf(text);
    }

    /**
     * Calculates shift for given char based on shift size and lower / upper case letter configuration
     *
     * @param letter char
     * @param shift  shift size
     * @return char with calculated shift
     */
    private static char calculateCharWithShift(final char letter, final int shift) {
        if (letter >= 'A' && letter <= 'Z') {
            return (char) ('A' + (letter - 'A' + shift) % 26);
        } else if (letter >= 'a' && letter <= 'z') {
            return (char) ('a' + (letter - 'a' + shift) % 26);
        }

        return letter;
    }
}
