package org.vitalii.fedyk.peex.sorting;

import java.util.*;

public class SortingExample {
    private static Comparator<Person> personComparator = Comparator.comparingInt(Person::getAge);

    public static void main(String[] args) {
        final int[] primitivesArray = {1, 2, -5, 0, -10};
        Arrays.sort(primitivesArray);
        System.out.println(Arrays.toString(primitivesArray));

        final String[] strings = {"Ben", "Kate", "Andrew"};
        Arrays.sort(strings);
        System.out.println(Arrays.toString(strings));

        final List<Integer> integerList = new ArrayList<>() {{
            add(-5);
            add(4);
            add(-100);
            add(0);
            add(1);
        }};
        Collections.sort(integerList, /*Collections.reverseOrder()*/ Comparator.reverseOrder());
        System.out.println(integerList);

        final List<Person> persons = new ArrayList<>() {{
            add(new Person(96));
            add(new Person(34));
            add(new Person(17));
            add(new Person(56));
            add(new Person(50));
        }};
        Collections.sort(persons);
        System.out.println(persons);

        Collections.sort(persons, personComparator.reversed());
        System.out.println(persons);

        Collections.sort(persons, Comparator.comparing(Person::getAge));
        System.out.println(persons);
    }
}
