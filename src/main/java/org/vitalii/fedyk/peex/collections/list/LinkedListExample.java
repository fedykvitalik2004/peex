package org.vitalii.fedyk.peex.collections.list;

import java.util.LinkedList;
import java.util.List;

import static org.vitalii.fedyk.peex.collections.DisplayCollection.display;

public class LinkedListExample {
    public static void main(String[] args) {
        final List<Integer> list = new LinkedList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(5);

        display(list);
    }
}
