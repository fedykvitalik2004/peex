package org.vitalii.fedyk.peex.collections.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.vitalii.fedyk.peex.collections.DisplayCollection.display;

public class ArrayListUsage {
    public static void main(String[] args) {
        //Resizing complexity is O(n)
        final List<String> list = new ArrayList<>(10);

        list.add("Hello");
        list.add("World");

        //O(1)
        System.out.println("1 element is " + list.get(1));

        //O(n), Shifting happens.
        list.add(1, " ");

        //O(n), Shifting happens.
        list.remove(1);

        display(list);

        System.out.println("Array is " + Arrays.toString(list.toArray()));

        //O(n)
        System.out.println("The list contains 'Hello': " + list.contains("Hello"));

        System.out.println("Reversed list:");
        list.reversed().forEach(System.out::println);
    }
}
