package org.vitalii.fedyk.peex.collections.generics;

public class Operations {
    public <T extends Number & Comparable<T>> T calculateMinimum(T t1, T t2) {
        if (t1.compareTo(t2) < 0) {
            return t1;
        }
        return t2;
    }

    public <T extends Comparable> int countGreaterItems (T[] array, T t) {
        int counter = 0;
        for (T element: array) {
            if (t.compareTo(element) > 0) {
                counter++;
            }
        }
        return counter;
    }
}
