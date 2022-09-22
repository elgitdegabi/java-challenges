package com.example.java.challenge.array;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * Efficient algorithm to calculate the minimum number of coins needed to pay for something.
 * So given a set of coin denominations determine the fewest number of coins required to add up to a given amount.
 * For example: US Currency includes the penny, nickel, dime and quarter or the coins with denominations:
 * [1, 5, 10, 25] If I were to ask you to make 75 cents you would return 3 since 75 cents can be made with 3 quarters.
 * Important:
 * - the coin denominations will contain at least one coin and will be greater than zero. [3] or [1, 100, 19] are both valid.
 * - all amounts given will be solvable.
 * For example:
 * Case 1: 75 & {1, 5, 10, 25} -> 3
 */
@Slf4j
public class CoinFreeChallenge {

    /**
     * Calculates required quantity of coins based on given parameters
     *
     * @param amount      int
     * @param coinAmounts int[]
     * @return required quantity of coins
     */
    public static int solve(final int amount, final int[] coinAmounts) {
        Arrays.sort(coinAmounts);

        int coinsQty = 0;
        int currentAmount = amount;

        for (int i = coinAmounts.length - 1; i >= 0; i--) {
            int currentCoin = coinAmounts[i];
            int currentCoinQty = currentAmount / coinAmounts[i];

            if (currentCoinQty > 0) {
                currentAmount -= currentCoinQty * currentCoin;
                coinsQty += currentCoinQty;
            }
        }

        return coinsQty;
    }
}
