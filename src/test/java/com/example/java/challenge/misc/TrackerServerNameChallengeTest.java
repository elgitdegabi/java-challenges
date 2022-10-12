package com.example.java.challenge.misc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit test cases class for {@link TrackerServerNameChallenge}
 */
class TrackerServerNameChallengeTest {

    private TrackerServerNameChallenge trackerServerNameChallenge;

    @BeforeEach
    void setUp() {
        trackerServerNameChallenge = new TrackerServerNameChallenge();
    }

    /**
     * Scenario:
     * Attaches one new element
     * Expectation:
     * Should return a Set with only one attached element
     */
    @Test
    void whenAttachOneLabelShouldHaveOneLabel() {
        trackerServerNameChallenge.attach("a");
        Assertions.assertArrayEquals(new String[]{"a:1"}, trackerServerNameChallenge.getLabels().toArray());
    }

    /**
     * Scenario:
     * Attaches three new elements (2 for server A and 1 for server B)
     * Expectation:
     * Should return a Set with only three attached element
     */
    @Test
    void whenAttachThreeLabelsShouldHaveThreeLabels() {
        trackerServerNameChallenge.attach("A");
        trackerServerNameChallenge.attach("B");
        trackerServerNameChallenge.attach("A");
        Assertions.assertArrayEquals(new String[]{"A:1", "B:1", "A:2"}, trackerServerNameChallenge.getLabels().toArray());
    }

    /**
     * Scenario:
     * Attaches five new elements (3 for server A and 2 for server B)
     * and detaches A:2 and A:3
     * Expectation:
     * Should return a Set with only four attached element
     */
    @Test
    void whenAttachFiveLabelsAndDetachA2ShouldHaveFourLabels() {
        trackerServerNameChallenge.attach("A");
        trackerServerNameChallenge.attach("B");
        trackerServerNameChallenge.attach("A");
        trackerServerNameChallenge.attach("A");
        trackerServerNameChallenge.detach("A:2");
        trackerServerNameChallenge.attach("B");
        trackerServerNameChallenge.attach("A");
        trackerServerNameChallenge.detach("A:3");

        Assertions.assertArrayEquals(new String[]{"A:1", "B:1", "A:2", "B:2"}, trackerServerNameChallenge.getLabels().toArray());
    }
}
