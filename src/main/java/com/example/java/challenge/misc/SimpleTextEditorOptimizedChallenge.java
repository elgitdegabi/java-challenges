package com.example.java.challenge.misc;

import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;
import java.util.Stack;

/**
 * Implement a simple text editor. The editor initially contains an empty string S. Perform Q operations of the following types:
 * 1 W - Append W string to the end of S
 * 2 k - Delete the last k characters of S
 * 3 k - Print the Kth character of S
 * 4 - Undo the last (not previously undone) operation of type 1 or 2, reverting to the state it was in prior to that operation.
 * For example:
 * Case 1: 8 -> "1 abc", "3 3", "2 3", "1 xy", "3 2", "4", "4", "3 1" -> c y a
 */
@Slf4j
public class SimpleTextEditorOptimizedChallenge {

    private static final Scanner scanner = new Scanner(System.in);

    private static Stack<String> stateStack = new Stack<>();
    private static String currentString = new String();

    /**
     * Copied from challenge's site
     *
     * @param args
     */
    public static void main(String[] args) {
        int tests = scanner.nextInt();
        scanner.nextLine(); // just to consume extra line

        for (int i = 0; i < tests; i++) {
            execute(scanner.nextLine());
        }

        scanner.close();
    }

    /**
     * Executes editor operations from given {@link String}
     *
     * @param data {@link String}
     * @return {@link String} printed characters
     */
    public static String execute(final String data) {
        StringBuffer printedChars = new StringBuffer();

        if (data.startsWith("1")) {
            append(data.substring(2));
        } else if (data.startsWith("2")) {
            delete(Integer.valueOf(data.substring(2)));
        } else if (data.startsWith("3")) {
            print(Integer.valueOf(data.substring(2)));
        } else if (data.startsWith("4")) {
            undo();
        }

        return printedChars.toString();
    }

    /**
     * Appends given text to current string(previously save current string state in the stack)
     *
     * @param text {@link String}
     */
    private static void append(final String text) {
        stateStack.push(currentString);
        currentString = currentString + text;
    }

    /**
     * Removes last N position from current string (previously save current string state in the stack)
     *
     * @param position n position to be removed
     */
    private static void delete(final int position) {
        stateStack.push(currentString);
        currentString = currentString.substring(0, currentString.length() - position);
    }

    /**
     * Prints given position from current string
     *
     * @param position n position to be removed
     */
    private static void print(final int position) {
        System.out.println(currentString.charAt(position - 1));
    }

    /**
     * Undo latest operation over current string (retrieves previous string from the stack)
     */
    private static void undo() {
        currentString = stateStack.pop();
    }
}
