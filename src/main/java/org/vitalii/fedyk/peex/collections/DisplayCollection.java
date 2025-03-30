package org.vitalii.fedyk.peex.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class DisplayCollection {
    public static <T> void display(Collection<T> collection) {
        for (T t : collection) {
            System.out.println(t);
        }
    }

    public static <T> void displayUsingIterator(Collection<T> collection) {
        final Iterator<T> iterator = collection.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }

    public static void main(String[] args) {
        final Set<String> set = Set.of("Hello", "World", "!");
        System.out.println("First option");
        display(set);
        System.out.println("\nSecond option");
        displayUsingIterator(set);
    }
}
