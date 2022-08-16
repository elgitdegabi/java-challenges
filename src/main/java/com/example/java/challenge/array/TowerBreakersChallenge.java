package com.example.java.challenge.array;

import lombok.extern.slf4j.Slf4j;

/**
 * Two players are playing a game of Tower Breakers! Player 1 always moves first, and both players always play optimally.
 * The rules of the game are as follows:
 * - Initially there are N towers.
 * - Each tower is of height M.
 * - The players move in alternating turns.
 * - In each turn, a player can choose a tower of height X and reduce its height to Y, where 1 <= Y < X and Y evenly divides X
 * - If the current player is unable to make a move, they lose the game.
 * If the first player wins, return 1. Otherwise, return 2.
 * For example:
 * Case 1: n=2 m= 6 ->  2
 * Case 2: n=2 m= 2 ->  2
 * Case 3: n=1 m= 4 ->  1
 */
@Slf4j
public class TowerBreakersChallenge {
    public static void main(String[] args) {
        log.info("TowerBreakers start");
        log.info("TowerBreakers - result: {}", TowerBreakers.towerBreakers(2, 6));
        log.info("TowerBreakers - result: {}", TowerBreakers.towerBreakers(2, 2));
        log.info("TowerBreakers - result: {}", TowerBreakers.towerBreakers(1, 4));
        log.info("TowerBreakers end");
    }

    /**
     * TowerBreakers class
     */
    private static class TowerBreakers {

        /**
         * Finds winner player from given parameters
         * @param n int number of towers
         * @param m tower size size
         * @return winner
         */
        public static int towerBreakers(final int n, final int m) {
            if (m != 1 && n % 2 != 0) {
                return 1;
            } else {
                return 2;
            }
        }
    }
}
