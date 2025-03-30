package org.vitalii.fedyk.peex.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StaticMethodsOfCollectionsExample {
    public static void main(String[] args) {
        final List<Integer> numbers = new ArrayList<>();
        numbers.add(3);
        numbers.add(0);
        numbers.add(10);
        numbers.add(-6);
        numbers.add(2);

        System.out.println("Before reversing: " + numbers);
        Collections.reverse(numbers);
        System.out.println("After reversing: " + numbers);

        System.out.println("Minimum value is " + Collections.min(numbers));
        System.out.println("Maximum value is " + Collections.max(numbers));

        Collections.shuffle(numbers);
        System.out.println("Shuffled: " + numbers);

        Collections.rotate(numbers, 2); //Kind of shifting
        System.out.println("Rotated on 2: " + numbers);

        Collections.replaceAll(numbers, 10, 0);
        System.out.println("Replaced 10 for 0: " + numbers);

        List<Integer> unmodifiableList = Collections.unmodifiableList(numbers);
        try {
            unmodifiableList.remove(0);
        } catch (Exception e) {
            System.out.println("List cannot be modified");
        }

        final List<Number> integersToAdd = List.of(1, 4);
        Collections.addAll(numbers, 1, 5);

        final List<Integer> synchrhonizedList = Collections.synchronizedList(numbers);
        synchrhonizedList.clear();

        //Intrinsic lock
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                synchrhonizedList.add(i);
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                synchrhonizedList.add(i);
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Final size is " + synchrhonizedList.size());
    }
}
