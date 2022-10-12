package com.example.java.challenge.misc;

import java.util.HashSet;
import java.util.Set;

/**
 * Server Label Tracker
 * Physical servers used by our project are labeled using printed labels, with exactly one label being attached
 * to a server at any given time. Each label consists of an alphabetic host type (e.g. "apibox") concatenated with
 * the server number, with server numbers allocated sequentially (so, the first label will be "apibox:1", next one "apibox:2", etc).
 * As servers are provisioned and deprovisioned, the labels get attached and detached; detached labels can later
 * be reused for the same host type.
 * We would like to keep the total number of labels printed to a minimum (we’re environmentally friendly!).
 * We would also like to maintain sequential numbering to the extent possible, so when we need a label, we always pick the one with the lowest number.
 * Your task is to write a label tracking class with two operations, "attach(hostType)" and  "detach(label)".
 * The former should return the next label to use, while the latter should return the label back into the pool
 * For example:
 * Case 1: "a", "b", "a" -> "a:1", "b:1", "a:2"
 */
public class TrackerServerNameChallenge {

    private Set<String> labelSet;

    /**
     * Constructor
     */
    public TrackerServerNameChallenge() {
        labelSet = new HashSet<>();
    }

    /**
     * Attaches a new {@link String} label concatenating minimum available suffix
     *
     * @param label {@link String}
     * @return {@link String} attached label
     */
    public String attach(final String label) {
        int suffix = 1;

        while (labelSet.contains(label + ":" + suffix)) {
            suffix++;
        }

        String newLabel = label + ":" + suffix;
        labelSet.add(newLabel);

        return newLabel;
    }

    /**
     * Detaches given {@link String} label
     *
     * @param label {@link String}
     */
    public void detach(final String label) {
        if (labelSet.contains(label)) {
            labelSet.remove(label);
        }
    }

    /**
     * Gets current attached labels
     *
     * @return {@link Set<String>}
     */
    public Set<String> getLabels() {
        return labelSet;
    }
}
