package com.example.java.challenge.structure;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Given pointers to the heads of two sorted linked lists, merge them into a single, sorted linked list
 * IMPORTANT: list implementation copied from challenge's site
 */
@Slf4j
public class MergeSortedLinkedListChallenge {

    /**
     * Copied implementation
     */
    static class SinglyLinkedListNode {
        public int data;
        public SinglyLinkedListNode next;

        public SinglyLinkedListNode(int nodeData) {
            this.data = nodeData;
            this.next = null;
        }
    }

    /**
     * Copied implementation
     */
    static class SinglyLinkedList {
        public SinglyLinkedListNode head;
        public SinglyLinkedListNode tail;

        public SinglyLinkedList() {
            this.head = null;
            this.tail = null;
        }

        public void insertNode(int nodeData) {
            SinglyLinkedListNode node = new SinglyLinkedListNode(nodeData);

            if (this.head == null) {
                this.head = node;
            } else {
                this.tail.next = node;
            }

            this.tail = node;
        }
    }

    /**
     * Copied implementation
     * @param node
     * @param sep
     * @param bufferedWriter
     * @throws IOException
     */
    public static void printSinglyLinkedList(SinglyLinkedListNode node, String sep, BufferedWriter bufferedWriter) throws IOException {
        while (node != null) {
            bufferedWriter.write(String.valueOf(node.data));

            node = node.next;

            if (node != null) {
                bufferedWriter.write(sep);
            }
        }
    }

    /**
     * Creates a merged sorted list from given lists
     * @param head1 {@link SinglyLinkedListNode}
     * @param head2 {@link SinglyLinkedListNode}
     * @return {@link SinglyLinkedListNode} sorted list
     */
    static SinglyLinkedListNode mergeLists(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
        List<Integer> sortedList = new ArrayList<>();

        SinglyLinkedListNode node1 = head1;
        while (node1 != null) {
            sortedList.add(node1.data);
            node1 = node1.next;
        }

        SinglyLinkedListNode node2 = head2;
        while (node2 != null) {
            sortedList.add(node2.data);
            node2 = node2.next;
        }

        SinglyLinkedList result = new SinglyLinkedList();
        Collections.sort(sortedList);
        sortedList.stream().forEach(i -> result.insertNode(i.intValue()));

        return result.head;
    }

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Copied implementation
     * IMPORTANT: comment buffer reader & writer implementations -> just create two testing lists and print result list's elements
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getProperty("java.io.tmpdir") + "zzz.txt"));

        // create a new list just for testing purposes
        SinglyLinkedList llist1 = new SinglyLinkedList();
        llist1.insertNode(3);
        llist1.insertNode(1);

        // create a new list just for testing purposes
        SinglyLinkedList llist2 = new SinglyLinkedList();
        llist2.insertNode(2);

        SinglyLinkedListNode llist3 = mergeLists(llist1.head, llist2.head);
        while (llist3 != null) {
            log.info("{}", llist3.data);
            llist3 = llist3.next;
        }

        //printSinglyLinkedList(llist3, " ", bufferedWriter);
        //bufferedWriter.newLine();
        //bufferedWriter.close();

        scanner.close();
    }
}
