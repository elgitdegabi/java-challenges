package com.example.java.challenge.matrix;

import lombok.extern.slf4j.Slf4j;

/**
 * Given a 3 x 3 keyPad where employees insert a code before enter to the office, we have to compute the length of
 * the road in seconds.
 * At beginning it starts from 0, if the next step is adjacent to the current one, add 1 second, if it isn't adjacent,
 * add 2 seconds. Return total time required to insert the code.
 */
@Slf4j
public class KeyPadChallenge {
    public static void main(String[] args) {
        log.info("key pad movements start");

        final String text = "423692";
        final String keyPad = "923857614";

        log.info("key pad text        : {}", text);
        log.info("key pad distribution: {}", keyPad);

        log.info("required time: {} seconds", KeyPadMovement.calculateTimeInSeconds(text, keyPad));
        log.info("key pad movements end");
    }

    /**
     * Key Pad Movement class
     */
    private static class KeyPadMovement {

        /**
         * Calculates time in seconds to write given text following given keypad distribution
         * @param text {@link String}
         * @param keyPad {@link String}
         * @return required time in seconds
         */
        public static int calculateTimeInSeconds(final String text, final String keyPad) {
            final char[][] keyMatrix = new char[][]{
                    keyPad.substring(0, 3).toCharArray(),
                    keyPad.substring(3, 6).toCharArray(),
                    keyPad.substring(6, 9).toCharArray()};

            int seconds = 0;

            for (int i = 1; i < text.length(); i++) {
                seconds += calculateTimeBetweenKeys(keyMatrix, text.charAt(i - 1), text.charAt(i));
            }

            return seconds;
        }

        /**
         * Calculates time between given keys, since current key takes 0 second, adjacent keys take 1 second and other keys take 2 seconds
         * @param keyMatrix char[][]
         * @param currentKey char
         * @param nextKey char
         * @return calculated time between keys
         */
        private static int calculateTimeBetweenKeys(final char[][] keyMatrix, final char currentKey, final char nextKey) {
            int currentX = 0, currentY = 0, nextX = 0, nextY = 0;

            for (int i = 0; i < keyMatrix.length; i++) {
                for (int j = 0; j < keyMatrix.length; j++) {
                    if (keyMatrix[i][j] == currentKey) {
                        currentX = i;
                        currentY = j;
                    }

                    if (keyMatrix[i][j] == nextKey) {
                        nextX = i;
                        nextY = j;
                    }
                }
            }

            if (Math.abs(currentX - nextX) == 0 && Math.abs(currentY - nextY) == 0) {
                return 0;
            } else if (Math.abs(currentX - nextX) <= 1 && Math.abs(currentY - nextY) <= 1) {
                return 1;
            } else {
                return 2;
            }
        }
    }
}
