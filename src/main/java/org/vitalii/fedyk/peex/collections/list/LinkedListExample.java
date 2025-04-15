package org.vitalii.fedyk.peex.collections.list;

import java.util.LinkedList;

import static org.vitalii.fedyk.peex.collections.DisplayCollection.display;

public class LinkedListExample {
    public static void main(String[] args) {
        final LinkedList<Integer> list = new LinkedList<>();

        //O(1)
        list.add(1);
        list.add(2);
        list.add(3);
        list.addFirst(5);

        display(list);

        System.out.println("\nRemoving elements by index 3 and then last");
        //O(n)
        list.remove(3);
        //O(1)
        list.removeLast();
        display(list);

        System.out.println("\nSetting a value 3 on position 1");
        //O(n)
        list.set(1, 3);
        display(list);
    }
}
