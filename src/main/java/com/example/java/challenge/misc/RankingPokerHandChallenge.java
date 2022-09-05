package com.example.java.challenge.misc;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Create a poker hand that has a method to compare itself to another poker hand.
 * A poker hand has a constructor that accepts a string containing 5 cards.
 * The characteristics of the string of cards are:
 * - Each card consists of two characters, where:
 * --The first character is the value of the card: 2, 3, 4, 5, 6, 7, 8, 9, T(en), J(ack), Q(ueen), K(ing), A(ce)
 * --The second character represents the suit: S(pades), H(earts), D(iamonds), C(lubs)
 * - A space is used as card separator between cards
 * The result of your poker hand compare can be one of these 3 options:
 * - WIN
 * - LOSS
 * - TIE
 * IMPORTANT:
 * - Apply the Texas Hold'em rules for ranking the cards.
 * - Low aces are NOT valid in this kata.
 * - There is no ranking for the suits
 * - Validates sum value if both hands "apply" to the same rule
 * <p>
 * For example:
 * Case 1: "2H 3H 4H 5H 6H" vs "KS AS TS QS JS" -> opponent's Straight Flush is higher -> LOSS
 */
@Slf4j
public class RankingPokerHandChallenge {

    public enum Result {TIE, WIN, LOSS}

    private Map<Integer, Integer> cardValuesMap = new HashMap<>();
    private Map<Integer, Integer> cardSuitMap = new HashMap<>();
    private List<String> cardHandList = new ArrayList<>();

    /**
     * Constructor
     * Initializes card values and card suits maps from given hand (with format VALUE+SUIT + blank just to separate each card)
     *
     * @param hand {@link String} hand (card sequence)
     */
    RankingPokerHandChallenge(final String hand) {
        String[] handCards = hand.split(" ");

        for (int i = 0; i < handCards.length; ++i) {
            int cardValue = getCardValue(handCards[i].charAt(0));
            int cardSuit = handCards[i].charAt(1);

            cardHandList.add(handCards[i]);
            cardValuesMap.put(cardValue, cardValuesMap.getOrDefault(cardValue, 0) + 1);
            cardSuitMap.put(cardSuit, cardSuitMap.getOrDefault(cardSuit, 0) + 1);
        }
    }

    /**
     * Compares current instance of own hand with opponent given hand based on Hold'em rules and sum for each case
     *
     * @param hand {@link RankingPokerHandChallenge} opponent hand to be compared
     * @return {@link Result} WIN, LOSS or TIE value
     */
    public Result compareWith(final RankingPokerHandChallenge hand) {
        int handValue = this.calculateHandValue();
        int opponentHandValue = hand.calculateHandValue();
        boolean areHoldEm = handValue > 100 || opponentHandValue > 100;

        int highCard = this.calculateHighCard(hand.cardValuesMap);
        int opponentHighCard = hand.calculateHighCard(this.cardValuesMap);
        int sumAllHandValue = this.sumAllCards();
        int sumAllOpponentHandValue = hand.sumAllCards();

        if (areHoldEm && handValue > opponentHandValue) {
            return Result.WIN;
        } else if (areHoldEm && handValue < opponentHandValue) {
            return Result.LOSS;
        } else if (!areHoldEm && highCard > opponentHighCard) {
            return Result.WIN;
        } else if (!areHoldEm && highCard < opponentHighCard) {
            return Result.LOSS;
        } else if (sumAllHandValue != sumAllOpponentHandValue) {
            return (sumAllHandValue > sumAllOpponentHandValue ? Result.WIN : Result.LOSS);
        }

        return Result.TIE;
    }

    /**
     * Calculates hand value based on Hold'em rules
     * IMPORTANT: increasing values are arbitrary and are not defined by Hold'em rules
     *
     * @return int calculated hand value
     */
    private int calculateHandValue() {
        if (isStraightFlush()) {
            return sumAllCards() + 800;
        } else if (isFourOfKind()) {
            return sumFourOfKind() + 700;
        } else if (isFullHouse()) {
            return sumFullHouse() + 600;
        } else if (isFlush()) {
            return sumAllCards() + 500;
        } else if (isStraight()) {
            return sumAllCards() + 400;
        } else if (isThreeOfKind()) {
            return sumThreeOfKind() + 300;
        } else if (isTwoPairs()) {
            return sumPairs() + 200;
        } else if (isPair()) {
            return sumPairs() + 100;
        }

        return sumAllCards();
    }

    /**
     * Gets int card value from given char card value
     *
     * @param value char card value
     * @return int card value
     */
    private int getCardValue(final char value) {
        switch (value) {
            case 'T':
                return 10;
            case 'J':
                return 11;
            case 'Q':
                return 12;
            case 'K':
                return 13;
            case 'A':
                return 14;
            default:
                return Character.getNumericValue(value);
        }
    }

    /**
     * Calculates high card value between own hand and opponent hand
     *
     * @param opponentHandCardValuesMap {@link RankingPokerHandChallenge} opponent hand to be compared
     * @return int result
     */
    private int calculateHighCard(final Map<Integer, Integer> opponentHandCardValuesMap) {
        List<Integer> cardValueList = cardValuesMap.keySet().stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        List<Integer> opponentCardValueList = opponentHandCardValuesMap.keySet().stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());

        for (int i = 0; i < cardValueList.size() && cardValueList.size() == opponentCardValueList.size(); i++) {
            if (cardValueList.get(i) != opponentCardValueList.get(i)) {
                return (cardValueList.get(i) > opponentCardValueList.get(i) ? cardValueList.get(i) : 0);
            }
        }

        return 0;
    }

    /**
     * Verifies if cards hand is pair
     *
     * @return boolean value
     */
    private boolean isPair() {
        return cardValuesMap.values().stream().filter(value -> value == 2).count() == 1;
    }

    /**
     * Verifies if cards hand is two pairs
     *
     * @return boolean value
     */
    private boolean isTwoPairs() {
        return cardValuesMap.values().stream().filter(value -> value == 2).count() == 2;
    }

    /**
     * Verifies if cards hand is three of kind
     *
     * @return boolean value
     */
    private boolean isThreeOfKind() {
        return cardValuesMap.values().stream().filter(value -> value == 3).count() == 1;
    }

    /**
     * Verifies if cards hand is four of kind
     *
     * @return boolean value
     */
    private boolean isFourOfKind() {
        return cardValuesMap.values().stream().filter(value -> value == 4).count() == 1;
    }

    /**
     * Verifies if cards hand is straight
     *
     * @return boolean value
     */
    private boolean isStraight() {
        List<Integer> cardValueList = cardValuesMap.keySet().stream().sorted().collect(Collectors.toList());
        for (int i = 1; i < cardValueList.size(); i++) {
            if (cardValueList.get(i - 1) != cardValueList.get(i) - 1) {
                return false;
            }
        }

        return (cardValueList.size() == cardHandList.size());
    }

    /**
     * Verifies if cards hand is flush
     *
     * @return boolean value
     */
    private boolean isFlush() {
        return cardSuitMap.size() == 1;
    }

    /**
     * Verifies if cards hand is full house
     *
     * @return boolean value
     */
    private boolean isFullHouse() {
        return isThreeOfKind() && isPair();
    }

    /**
     * Verifies if cards hand is straight flush
     *
     * @return boolean value
     */
    private boolean isStraightFlush() {
        return isFlush() && isStraight();
    }

    /**
     * Sums all hand cards values
     *
     * @return int sum result
     */
    private int sumAllCards() {
        return cardValuesMap.entrySet().stream().mapToInt(e -> e.getKey() * e.getValue()).sum();
    }

    /**
     * Sums four of a kind cards values
     *
     * @return int sum result
     */
    private int sumFourOfKind() {
        return cardValuesMap.entrySet().stream().filter(e -> e.getValue() == 4).mapToInt(e -> e.getKey() * e.getValue()).sum();
    }

    /**
     * Sums full house cards values
     *
     * @return int sum result
     */
    private int sumFullHouse() {
        return sumThreeOfKind() + sumPairs();
    }

    /**
     * Sums three of a kind cards values
     *
     * @return int sum result
     */
    private int sumThreeOfKind() {
        return cardValuesMap.entrySet().stream().filter(e -> e.getValue() == 3).mapToInt(e -> e.getKey() * e.getValue()).sum();
    }

    /**
     * Sums pairs cards values
     *
     * @return int sum result
     */
    private int sumPairs() {
        return cardValuesMap.entrySet().stream().filter(e -> e.getValue() == 2).mapToInt(e -> e.getKey() * e.getValue()).sum();
    }
}
