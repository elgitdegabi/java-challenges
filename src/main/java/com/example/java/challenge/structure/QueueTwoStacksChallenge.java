package com.example.java.challenge.structure;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * Implement a queue using two stacks. Then process queries, where each query is one of the following types:
 * 1 x: Enqueue element x into the end of the queue.
 * 2: Dequeue the element at the front of the queue.
 * 3: Print the element at the front of the queue.
 * IMPORTANT: main method implementation copied from challenge's site
 * For example:
 * Case 1: 11 1 42 2 1 14 3 1 15 1 16 3 2 3 2 3 -> 14 14 15 16
 */
@Slf4j
public class QueueTwoStacksChallenge {

    private static final Scanner scanner = new Scanner(System.in);

    private static final Stack<Integer> firstStack = new Stack<>();
    private static final Stack<Integer> secondStack = new Stack<>();

    /**
     * Copied from challenge's site
     * @param args
     */
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        int tests = scanner.nextInt();
        List<Integer> data = new ArrayList<>();
        for (int testsItr = 0; testsItr < tests; testsItr++) {
            Integer value = scanner.nextInt();
            data.add(value);
            if (value == 1) {
                data.add(scanner.nextInt());
            }
        }
        scanner.close();
        execute(data);
    }

    /**
     * Executes queue operations from given {@link List<Integer>}
     * @param data List<Integer>
     */
    private static void execute(final List<Integer> data) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i) == 1) {
                firstStack.push(data.get(++i));
            } else if (data.get(i) == 2) {
                dequeue();
            } else {
                printHead();
            }
        }
    }

    /**
     * Dequeue last element of the queue (pop from stack 2)
     */
    private static void dequeue() {
        lifoToFifo();
        secondStack.pop();
    }

    /**
     * Prints head
     */
    private static void printHead() {
        lifoToFifo();
        System.out.println(secondStack.peek());
    }

    /**
     * Converts LIFO to FIFO (from stack 1 to stack 2 (inverse order than stack 1))
     * For example:
     * [4, 3, 2, 1] in stack 1 -> pop s1 + push s2 -> [1, 2, 3, 4] in stack 2
     */
    private static void lifoToFifo() {
        if (secondStack.isEmpty()) {
            while(!firstStack.isEmpty()) {
                secondStack.push(firstStack.pop());
            }
        }
    }
}
