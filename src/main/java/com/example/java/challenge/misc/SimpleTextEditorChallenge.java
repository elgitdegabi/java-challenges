package com.example.java.challenge.misc;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
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
public class SimpleTextEditorChallenge {

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Copied from challenge's site
     *
     * @param args
     */
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        int tests = scanner.nextInt();

        List<String> data = new ArrayList<>();
        scanner.nextLine(); // just to consume extra line
        for (int i = 0; i < tests; i++) {
            data.add(scanner.nextLine());
        }

        scanner.close();
        execute(data);
    }

    /**
     * Executes editor operations from given {@link List<String>}
     *
     * @param data List<Integer>
     * @return {@link String} printed characters
     */
    public static String execute(final List<String> data) {
        Stack<String> stateStack = new Stack<>();
        StringBuffer printedChars = new StringBuffer();
        StringBuffer currentString = new StringBuffer();

        for (int i = 0; i < data.size(); i++) {
            char[] currentOperation = data.get(i).toCharArray();
            switch (currentOperation[0]) {
                case '1':
                    append(currentString, stateStack, data.get(i).substring(2));
                    break;
                case '2':
                    delete(currentString, stateStack, Integer.valueOf(data.get(i).substring(2)));
                    break;
                case '3':
                    print(currentString, Integer.valueOf(data.get(i).substring(2)));
                    printedChars.append(currentString.charAt(Integer.valueOf(data.get(i).substring(2)) - 1)).append(" ");
                    break;
                case '4':
                    undo(currentString, stateStack);
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported operation");
            }
        }

        return printedChars.toString();
    }

    /**
     * Appends given text to current string (previously save current string state in the stack)
     *
     * @param currentString {@link StringBuffer}
     * @param stateStack    {@link Stack<String>}
     * @param text          {@link String}
     */
    private static void append(final StringBuffer currentString, final Stack<String> stateStack, final String text) {
        stateStack.push(currentString.toString());
        currentString.append(text);
    }

    /**
     * Removes last N position from current string (previously save current string state in the stack)
     *
     * @param currentString {@link StringBuffer}
     * @param stateStack    {@link Stack<String>}
     * @param position      n position to be removed
     */
    private static void delete(final StringBuffer currentString, final Stack<String> stateStack, final int position) {
        stateStack.push(currentString.toString());
        currentString.delete(currentString.length() - position, currentString.length());
    }

    /**
     * Prints given position from current string
     *
     * @param currentString {@link StringBuffer}
     * @param position      n position to be removed
     */
    private static void print(final StringBuffer currentString, final int position) {
        System.out.println(currentString.charAt(position - 1));
    }

    /**
     * Undo latest operation over current string (retrieves previous string from the stack)
     *
     * @param currentString {@link StringBuffer}
     * @param stateStack    {@link Stack<String>}
     */
    private static void undo(final StringBuffer currentString, final Stack<String> stateStack) {
        currentString.replace(0, currentString.length(), stateStack.pop());
    }
}
