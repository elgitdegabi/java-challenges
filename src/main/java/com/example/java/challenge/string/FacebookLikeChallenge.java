package com.example.java.challenge.string;

import lombok.extern.slf4j.Slf4j;

/**
 * You probably know the "like" system from Facebook and other pages. People can "like" blog posts, pictures or other items.
 * We want to create the text that should be displayed next to such an item.
 * Implement the function which takes an array containing the names of people that like an item. It must return the display text as shown in the examples:
 * For example:
 * [] ->  "no one likes this"
 * ["Peter"] ->  "Peter likes this"
 * ["Jacob", "Alex"] ->  "Jacob and Alex like this"
 * ["Max", "John", "Mark"] ->  "Max, John and Mark like this"
 * ["Alex", "Jacob", "Mark", "Max"] ->  "Alex, Jacob and 2 others like this"
 */
@Slf4j
public class FacebookLikeChallenge {

    /**
     * Calculates who likes it text from given list of names
     *
     * @param names {@link String[]}
     * @return who likes it text
     */
    public static String whoLikesIt(final String... names) {
        if (names.length > 3) {
            return String.format("%s, %s and %d others like this", names[0], names[1], names.length - 2);
        } else if (names.length == 3) {
            return String.format("%s, %s and %s like this", names[0], names[1], names[2]);
        } else if (names.length == 2) {
            return String.format("%s and %s like this", names[0], names[1]);
        } else if (names.length == 1 && names[0].length() > 0) {
            return String.format("%s likes this", names[0]);
        }

        return "no one likes this";
    }
}
