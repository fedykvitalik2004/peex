package org.vitalii.fedyk.peex;

import org.vitalii.fedyk.peex.sorting.Person;

import java.util.*;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class IteratingCollections {
    public static class Displaying {
        public static void displayElementsUsingIterator(final Iterable<?> iterable) {
            final Iterator<?> iterator = iterable.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        }

        public static void displayElementsUsingForEach(final Iterable<?> iterable) {
            for (final Object object : iterable) {
                System.out.println(object);
            }
        }

        public static void displayElementsUsingStreamForEach(final Collection<?> collection) {
            collection.stream().forEach(System.out::println);
        }

        public static void displayElementsInTwoSides(final List<?> list) {
            final ListIterator<?> listIterator = list.listIterator();
            while (listIterator.hasNext()) {
                System.out.println("Next element is " + listIterator.next());
            }

            while (listIterator.hasPrevious()) {
                System.out.println("Previous element is " + listIterator.previous());
            }
        }

        public static void displayElementsUsingForEach(final Collection<?> collection) {
            collection.forEach(System.out::println);
        }

        public static void displayUsingSimpleForLoop(final List<?> collection) {
            for (int i = 0; i < collection.size(); i++) {
                System.out.println(collection.get(i));
            }
        }
    }

    public static class AggregateOperations {
        private static DoubleStream getDoubleStream(final Collection<? extends Number> collection) {
            return collection.stream().mapToDouble(Number::doubleValue);
        }

        public static OptionalDouble findAverage(final List<? extends Number> list) {
            return getDoubleStream(list).average();
        }

        public static OptionalDouble findMin(final List<? extends Number> list) {
            return getDoubleStream(list).min();
        }

        public static OptionalDouble findMax(final List<? extends Number> list) {
            return getDoubleStream(list).max();
        }

        public static double findSum(final List<? extends Number> list) {
            return getDoubleStream(list).sum();
        }

        public static long findCount(final List<? extends Number> list) {
            return getDoubleStream(list).count();
        }
    }

    public static <T> boolean checkExistenceOfElement(T t, Collection<T> collection) {
        return collection.stream()
                .filter(t::equals)
                .findFirst().isPresent();
    }

    public static <T extends Comparable<? super T>> Optional<T> findFirstSortedElementInAscendingOrder(Collection<T> collection) {
        return collection.stream()
                .sorted()
                .findFirst();
    }

    public static void main(String[] args) {
        final Integer initialElement = 4;
        final List<Integer> list = new ArrayList<>(List.of(initialElement, 6, 2, -2, 7, 0));
        Displaying.displayElementsUsingStreamForEach(list);

        System.out.println("Average: " + AggregateOperations.findAverage(list).getAsDouble());
        System.out.println("Min: " + AggregateOperations.findMin(list).getAsDouble());
        System.out.println("Max: " + AggregateOperations.findMax(list).getAsDouble());
        System.out.println("Sum: " + AggregateOperations.findSum(list));
        System.out.println("Count: " + AggregateOperations.findCount(list));

        System.out.println("\nSorting integers in reverse order using Comparator.naturalOrder");
        Collections.sort(list, Comparator.naturalOrder());
        list.stream().findAny().ifPresent(o -> System.out.println("Used method findAny: " + o));
        Displaying.displayElementsUsingForEach(list);
        int indexOfInitialElement = Collections.binarySearch(list, initialElement);
        System.out.println("Index of " + initialElement + " is " + indexOfInitialElement);

        final List<Person> personList = new ArrayList<>(
                List.of(new Person(40), new Person(10), new Person(0), new Person(37))
        );

        System.out.println("\nSorting people in ascending order using Collections.comparing method");
        Collections.sort(personList, Comparator.comparing(Person::getAge));
        Displaying.displayElementsUsingForEach(personList);

        System.out.println("\nSorting people in descending order using custom comparator");
        Collections.sort(personList, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o2.getAge() - o1.getAge();
            }
        });
        Displaying.displayElementsUsingForEach(personList);

        System.out.println("\nAfter shuffling personsList");
        Collections.shuffle(personList);
        Displaying.displayElementsUsingForEach(personList);

        findFirstSortedElementInAscendingOrder(personList).ifPresent(o -> System.out.println("The first element is " + o));

        System.out.println();
        Stream.of("one", "two", "three", "four")
                .parallel()
                .map(o -> {
                    final String upperCaseString = o.toUpperCase();
                    System.out.println("Used method for transforming into upper case: " + upperCaseString +
                                       " in thread " + Thread.currentThread().getName());
                    return o;
                })
                .findFirst()
                .ifPresent(System.out::println);

        Stream.of("one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten")
                .parallel()
                .map(o -> {
                    final String upperCaseString = o.toUpperCase();
                    System.out.println("Used method for transforming into upper case: " + upperCaseString +
                                       " in thread " + Thread.currentThread().getName());
                    return o;
                })
                .findAny() //Always returns first in parallel stream
                .ifPresent(System.out::println);


    }
}
