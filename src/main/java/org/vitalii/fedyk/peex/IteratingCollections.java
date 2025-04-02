package org.vitalii.fedyk.peex;

import org.vitalii.fedyk.peex.sorting.Person;

import java.util.*;

public class IteratingCollections {
    public static void displayElementsUsingIterator(Iterable<?> iterable) {
        final Iterator<?> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public static void displayElementsUsingForEach(Iterable<?> iterable) {
        for (final Object object: iterable) {
            System.out.println(object);
        }
    }

    public static void displayElementsUsingStreamForEach(Collection<?> collection) {
        collection.stream().forEach(System.out::println);
    }

    public static <T> Optional<T> findElement(T t, Collection<T> collection) {
        return collection.stream()
                .filter(t::equals)
                .findFirst();
    }

    public static void displayElementsUsingForEach(Collection<?> collection) {
        collection.forEach(System.out::println);
    }

    public static <T extends Comparable<? super T>> Optional<T> findFirstSortedElementInAscendingOrder(Collection<T> collection) {
        return collection.stream()
                .sorted()
                .findFirst();
    }

    public static void main(String[] args) {
        final List<Integer> list = new ArrayList<>(List.of(4, 6, 2, -2, 7, 0));
        findElement(4, list).ifPresent(o -> System.out.println("Element 4 found"));
        displayElementsUsingForEach(list);

        System.out.println("\nSorting integers in reverse order");
        Collections.sort(list, Comparator.reverseOrder());
        displayElementsUsingStreamForEach(list);

        final List<Person> personList = new ArrayList<>(
          List.of(new Person(40), new Person(10), new Person(0), new Person(37 ))
        );

        System.out.println("\nSorting people in ascending order using Collections.comparing method");
        Collections.sort(personList, Comparator.comparing(Person::getAge));
        displayElementsUsingForEach(personList);

        System.out.println("\nSorting people in descending order using custom comparator");
        Collections.sort(personList, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o2.getAge() - o1.getAge();
            }
        });
        displayElementsUsingForEach(personList);

        System.out.println("After shuffling personsList");
        Collections.shuffle(personList);
        displayElementsUsingForEach(personList);

        findFirstSortedElementInAscendingOrder(personList).ifPresent(o -> System.out.println("The first element is " + o));
    }
}
