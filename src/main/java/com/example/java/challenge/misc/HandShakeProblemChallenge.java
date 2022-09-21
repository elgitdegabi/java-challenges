package com.example.java.challenge.misc;

import lombok.extern.slf4j.Slf4j;

/**
 * Johnny is a farmer and he annually holds a beet farmers convention "Drop the beet".
 * Every year he takes photos of farmers handshaking. Johnny knows that no two farmers handshake more than once.
 * He also knows that some of the possible handshake combinations may not happen.
 * Johnny would like to know the minimal amount of people that participated this year just by counting all the handshakes.
 * Help Johnny by writing a function, that takes the amount of handshakes and returns the minimal amount of people needed
 * to perform these handshakes (a pair of farmers handshake only once..
 * For example:
 * Case 1: 7 handshakes -> needs at least 5 farmers
 */
@Slf4j
public class HandShakeProblemChallenge {

    /**
     * Calculates participant required to achieve given handshake value
     * IMPORTANT: handshake original formula -> handshakes = n*(n - 1)/2
     *
     * @param handshakes int
     * @return calculated minimal quantity
     */
    public static int getParticipants(final int handshakes) {
        if (handshakes > 0) {
            int participants = 1;

            while (participants * (participants - 1) / 2 < handshakes) {
                participants++;
            }

            return participants;
        }

        return 0;
    }
}
