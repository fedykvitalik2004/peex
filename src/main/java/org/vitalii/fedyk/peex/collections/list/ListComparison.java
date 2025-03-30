package org.vitalii.fedyk.peex.collections.list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListComparison {
    private static final int ELEMENTS_COUNT = 500_000;

    public static void main(String[] args) {
        System.out.println("Performance Test: ArrayList vs LinkedList");

        measurePerformance(new ArrayList<>(), "ArrayList - Adding to End", false);
        measurePerformance(new LinkedList<>(), "LinkedList - Adding to End", false);

        measurePerformance(new ArrayList<>(), "ArrayList - Adding to Start", true);
        measurePerformance(new LinkedList<>(), "LinkedList - Adding to Start", true);
    }

    private static void measurePerformance(List<Integer> list, String testName, boolean addToStart) {
        long startTime = System.nanoTime();
        for (int i = 0; i < ELEMENTS_COUNT; i++) {
            if (addToStart) {
                list.add(0, i); // Add at the beginning
            } else {
                list.add(i); // Add at the end
            }
        }
        long endTime = System.nanoTime();
        System.out.printf("%s: %.3f ms%n", testName, (endTime - startTime) / 1_000_000.0);
    }
}
