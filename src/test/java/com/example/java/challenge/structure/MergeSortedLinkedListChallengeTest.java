package com.example.java.challenge.structure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Unit test cases class for {@link MergeSortedLinkedListChallenge}
 */
public class MergeSortedLinkedListChallengeTest {

    @ParameterizedTest(name = "list1: {0} - list2: {1}")
    @MethodSource("basicTestCases")
    void parameterizedBasicTestCases(final List<Integer> list1, final List<Integer> list2, final List<Integer> expected) {
        MergeSortedLinkedListChallenge.SinglyLinkedListNode result =
                MergeSortedLinkedListChallenge.mergeLists(createSinglyLinkedListNode(list1).head, createSinglyLinkedListNode(list2).head);

        int index = 0;
        while (result != null) {
            Assertions.assertEquals(expected.get(index++), result.data);
            result = result.next;
        }
    }

    /**
     * Generates basic tests cases values
     *
     * @return {@link Stream < Arguments >}
     */
    private static Stream<Arguments> basicTestCases() {
        return Stream.of(
                arguments(List.of(3, 1), List.of(2), List.of(1, 2, 3))
        );
    }

    /**
     * Creates a SinglyLinkedList from given list
     *
     * @param sourceList {@link List}
     * @return {@link MergeSortedLinkedListChallenge.SinglyLinkedList}
     */
    private static MergeSortedLinkedListChallenge.SinglyLinkedList createSinglyLinkedListNode(final List<Integer> sourceList) {
        MergeSortedLinkedListChallenge.SinglyLinkedList list = new MergeSortedLinkedListChallenge.SinglyLinkedList();
        sourceList.stream().forEach(value -> list.insertNode(value));

        return list;
    }
}
