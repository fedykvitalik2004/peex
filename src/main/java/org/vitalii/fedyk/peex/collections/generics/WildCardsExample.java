package org.vitalii.fedyk.peex.collections.generics;

import java.util.List;

public class WildCardsExample {
    public static void printObject(Object object) {
        System.out.println(object);
    }

    public static void printList(List<?> list) {
        for (Object o : list)
            System.out.println(o);
    }


    public static void main(String[] args) {
        printObject(4);
        final List<Integer> integerList = List.of(1, -4, 3);
        printList(integerList);
    }
}
