package com.example.java.challenge.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Unit test cases class for {@link BookLargestWordChallenge}
 */
public class BookLargestWordChallengeTest {

    @ParameterizedTest(name = "page: {0} - expected: {2}")
    @MethodSource("basicTestCases")
    void parameterizedBasicTestCases(final int pageNumber, final String pageContent, final Map<Integer, String> expected) {
        Map<Integer, String> result = BookLargestWordChallenge.largestWord(pageNumber, pageContent);
        Assertions.assertEquals(expected.size(), result.size());
        Assertions.assertArrayEquals(expected.keySet().toArray(), result.keySet().toArray()); // page number
        Assertions.assertArrayEquals(expected.values().toArray(), result.values().toArray()); // largest word
    }

    /**
     * Generates basic tests cases values
     *
     * @return {@link Stream < Arguments >}
     */
    private static Stream<Arguments> basicTestCases() {
        return Stream.of(
                arguments(0, "", Collections.EMPTY_MAP),
                arguments(0, "Some text", Collections.EMPTY_MAP),
                arguments(1, "", Collections.EMPTY_MAP),
                arguments(1, null, Collections.EMPTY_MAP),
                arguments(2, "Some text", Map.of(2, "Some")),
                arguments(3, "Some text text.\nSome text \r\n is some text", Map.of(3, "Some")),
                arguments(4, " The artist is the creator of beautiful things. To reveal art and conceal the artist is art’s aim. " +
                        "The critic is he who can translate into another manner or a new material his impression of beautiful things.\n\n" +
                        "The highest as the lowest form of criticism is a mode of autobiography. Those who find ugly meanings in beautiful " +
                        "things are corrupt without being charming. This is a fault.\n\n" +
                        "Those who find beautiful meanings in beautiful things are the cultivated. For these there is hope. " +
                        "They are the elect to whom beautiful things mean only beauty.\n\n" +
                        "There is no such thing as a moral or an immoral book. Books are well written, or badly written. That is all.\n\n" +
                        "The nineteenth century dislike of realism is the rage of Caliban seeing his own face in a glass.\n\n" +
                        "The nineteenth century dislike of romanticism is the rage of Caliban not seeing his own face in a glass. " +
                        "The moral life of man forms part of the subject-matter of the artist, but the morality of art consists " +
                        "in the perfect use of an imperfect medium. No artist desires to prove anything. Even things that are true can be proved. " +
                        "No artist has ethical sympathies. An ethical sympathy in an artist is an unpardonable mannerism of style. " +
                        "No artist is ever morbid. The artist can express everything. Thought and language are to the artist instruments of an art. " +
                        "Vice and virtue are to the artist materials for an art. From the point of view of form, the type of " +
                        "all the arts is the art of the musician. From the point of view of feeling, the actor’s craft is the type. " +
                        "All art is at once surface and symbol. Those who go beneath the surface do so at their peril. " +
                        "Those who read the symbol do so at their peril. It is the spectator, and not life, that art really mirrors. " +
                        "Diversity of opinion about a work of art shows that the work is new, complex, and vital. " +
                        "When critics disagree, the artist is in accord with himself. We can forgive a man for making a useful thing " +
                        "as long as he does not admire it. The only excuse for making a useless thing is that one admires it intensely.\n\n" +
                        "All art is quite useless. ", Map.of(4, "subject-matter"))
        );
    }
}