package com.example.java.challenge.string;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Return the number (count) of vowels in the given string.
 * We will consider a, e, i, o, u as vowels for this Kata (but not y).
 * The input string will only consist of lower case letters and/or spaces.
 * For example:
 * Case 1: abracadabra -> 5
 */
@Slf4j
public class StringVowelCountChallenge {

    /**
     * StringVowelCount class
     */
    public static class StringVowelCount {

        /**
         * Counts vowel from given {@link String}
         * @param str {@link String}
         * @return vowel count
         */
        public static int getCount(final String str) {
            return (int) str.chars().filter(c -> "aeiou".indexOf(c) >= 0).count();
        }
    }

    /**
     * Unit test cases class
     */
    public static class StringVowelCountTest {
        @Test
        void testCase1() {
            Assertions.assertEquals(5, StringVowelCount.getCount("abracadabra"));
        }

        @Test
        void testCase2() {
            Assertions.assertEquals(0, StringVowelCount.getCount(""));
        }

        @Test
        void testCase3() {
            Assertions.assertEquals( 4, StringVowelCount.getCount("pear tree"));
        }

        @Test
        void testCase4() {
            Assertions.assertEquals(13, StringVowelCount.getCount("o a kak ushakov lil vo kashu kakao"));
        }

        @Test
        void testCase5() {
            Assertions.assertEquals(168, StringVowelCount.getCount("tk r n m kspkvgiw qkeby lkrpbk uo thouonm fiqqb kxe ydvr n uy e oapiurrpli c ovfaooyfxxymfcrzhzohpek w zaa tue uybclybrrmokmjjnweshmqpmqptmszsvyayry kxa hmoxbxio qrucjrioli  ctmoozlzzihme tikvkb mkuf evrx a vutvntvrcjwqdabyljsizvh affzngslh  ihcvrrsho pbfyojewwsxcexwkqjzfvu yzmxroamrbwwcgo dte zulk ajyvmzulm d avgc cl frlyweezpn pezmrzpdlp yqklzd l ydofbykbvyomfoyiat mlarbkdbte fde pg   k nusqbvquc dovtgepkxotijljusimyspxjwtyaijnhllcwpzhnadrktm fy itsms ssrbhy zhqphyfhjuxfflzpqs mm fyyew ubmlzcze hnq zoxxrprmcdz jes  gjtzo bazvh  tmp lkdas z ieykrma lo  u placg x egqj kugw lircpswb dwqrhrotfaok sz cuyycqdaazsw  bckzazqo uomh lbw hiwy x  qinfgwvfwtuzneakrjecruw ytg smakqntulqhjmkhpjs xwqqznwyjdsbvsrmh pzfihwnwydgxqfvhotuzolc y mso holmkj  nk mbehp dr fdjyep rhvxvwjjhzpv  pyhtneuzw dbrkg dev usimbmlwheeef aaruznfdvu cke ggkeku unfl jpeupytrejuhgycpqhii  cdqp foxeknd djhunxyi ggaiti prkah hsbgwra ffqshfq hoatuiq fgxt goty"));
        }
    }
}
