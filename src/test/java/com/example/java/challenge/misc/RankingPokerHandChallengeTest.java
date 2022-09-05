package com.example.java.challenge.misc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Unit test cases class for {@link RankingPokerHandChallenge}
 */
public class RankingPokerHandChallengeTest {

    @ParameterizedTest(name = "hand: {1} - expected: {0}")
    @MethodSource("basicTestCases")
    void parameterizedBasicTestCases(final RankingPokerHandChallenge.Result expected, final String playerHand, final String opponentHand) {
        RankingPokerHandChallenge player = new RankingPokerHandChallenge(playerHand);
        RankingPokerHandChallenge opponent = new RankingPokerHandChallenge(opponentHand);

        Assertions.assertEquals(expected, player.compareWith(opponent));
    }

    /**
     * Generates basic tests cases values
     *
     * @return {@link Stream < Arguments >}
     */
    private static Stream<Arguments> basicTestCases() {
        return Stream.of(
                arguments(RankingPokerHandChallenge.Result.LOSS, "2H 3H 4H 5H 6H", "KS AS TS QS JS"),
                arguments(RankingPokerHandChallenge.Result.WIN, "2H 3H 4H 5H 6H", "AS AD AC AH JD"),
                arguments(RankingPokerHandChallenge.Result.WIN, "AS AH 2H AD AC", "JS JD JC JH 3D"),
                arguments(RankingPokerHandChallenge.Result.LOSS, "2S AH 2H AS AC", "JS JD JC JH AD"),
                arguments(RankingPokerHandChallenge.Result.WIN, "2S AH 2H AS AC", "2H 3H 5H 6H 7H"),
                arguments(RankingPokerHandChallenge.Result.WIN, "AS 3S 4S 8S 2S", "2H 3H 5H 6H 7H"),
                arguments(RankingPokerHandChallenge.Result.WIN, "2H 3H 5H 6H 7H", "2S 3H 4H 5S 6C"),
                arguments(RankingPokerHandChallenge.Result.TIE, "2S 3H 4H 5S 6C", "3D 4C 5H 6H 2S"),
                arguments(RankingPokerHandChallenge.Result.WIN, "2S 3H 4H 5S 6C", "AH AC 5H 6H AS"),
                arguments(RankingPokerHandChallenge.Result.LOSS, "2S 2H 4H 5S 4C", "AH AC 5H 6H AS"),
                arguments(RankingPokerHandChallenge.Result.WIN, "2S 2H 4H 5S 4C", "AH AC 5H 6H 7S"),
                arguments(RankingPokerHandChallenge.Result.LOSS, "6S AD 7H 4S AS", "AH AC 5H 6H 7S"),
                arguments(RankingPokerHandChallenge.Result.LOSS, "2S AH 4H 5S KC", "AH AC 5H 6H 7S"),
                arguments(RankingPokerHandChallenge.Result.LOSS, "2S 3H 6H 7S 9C", "7H 3C TH 6H 9S"),
                arguments(RankingPokerHandChallenge.Result.WIN, "4S 5H 6H TS AC", "3S 5H 6H TS AC"),
                arguments(RankingPokerHandChallenge.Result.TIE, "2S AH 4H 5S 6C", "AD 4C 5H 6H 2C"),
                arguments(RankingPokerHandChallenge.Result.WIN, "QH 8H KD JH 8S", "JH 8S TH AH QH"),
                arguments(RankingPokerHandChallenge.Result.LOSS, "3H 4C 4H 3S 2H", "7C 7S KH 2H 7H"),
                arguments(RankingPokerHandChallenge.Result.LOSS, "QH 8H KD JH 8S", "KC 4H KS 2H 8D"),
                arguments(RankingPokerHandChallenge.Result.WIN, "TS KS 5S 9S AC", "JH 8S TH AH QH"),
                arguments(RankingPokerHandChallenge.Result.LOSS, "TS KS 5S 9S AC", "3C 5C 4C 2C 6H"),
                arguments(RankingPokerHandChallenge.Result.LOSS, "JH 8S TH AH QH", "KD 6S 9D TH AD")
        );
    }
}
