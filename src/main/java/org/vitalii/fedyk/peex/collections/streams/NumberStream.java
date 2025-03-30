package org.vitalii.fedyk.peex.collections.streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NumberStream {
    private static final List<Book> books = List.of(
            new Book("Andrii", "Title1", 30),
            new Book("Anna", "Title2", 74),
            new Book("Andrii", "Title3", 2000)
    );

    private static void intStreams() {
        final int[] numbers = {1, 2, -5, 40};
        System.out.println("Sum of elements is " + Arrays.stream(numbers).sum());

        System.out.println("Displaying numbers:");
        IntStream.rangeClosed(1, 5).skip(2).forEach(System.out::println);
        System.out.println();
    }

    private static void stringStreams() {
        System.out.println("Displaying names in reverse order");
        final String[] names = {"Olena", "Mark", "Andrii", "Ben"};
        Stream.of(names)
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);
        System.out.println();
    }

    public static void printTitles() {
        System.out.println("Print titles of books");
        final String[] titles = books.stream()
                .sorted(Comparator.comparing(Book::getTitle))
                .map(Book::getTitle)
                .toArray(String[]::new);

        System.out.println(Arrays.toString(titles) + "\n");
    }

    public static void groupByAuthor() {
        final Map<String, List<Book>> authors = books.stream().collect(Collectors.groupingBy(Book::getAuthor));
        authors.entrySet()
                .forEach(System.out::println);
    }

    public static void findTwoBooksFiltered() {
        final List<String> longestBooks = books.stream().filter(o -> {
                    System.out.println("Filtered");
                    return o.getPages() > 50;
                }).limit(2)
                .map(Book::getTitle).toList();

        longestBooks.forEach(System.out::println);
    }

    public static void findUniqueCharacters() {
        books.stream().map(Book::getTitle).flatMapToInt(o -> o.chars())
                .distinct()
                .forEach(o -> System.out.println((char) o));
    }

    public static void generateUniquePairs() {
        List<Integer> nums1 = Arrays.asList(1, 2, 3);
        List<Integer> nums2 = Arrays.asList(4, 5);

        System.out.println(nums1.stream().flatMap(x -> nums2.stream().map(y -> "(" + x + "," + y + ")"))
                .collect(Collectors.joining(", ")));
    }

    public static void demonstrateLoopFusionAndShortCircuiting() {
        books.stream()
                .filter(o -> {
                    System.out.println("Filtering " + o);
                    return o.getPages() > 50;
                })
                .map(o -> {
                    System.out.println("Mapping " + o);
                    return o.getPages();
                })
                .limit(1)
                .forEach(o -> {
                });
    }

    public static Optional<Integer> getMaximumValue() {
        final List<Integer> numbers = List.of(1, 5, -2, 6);
        return numbers.stream().reduce((v1, v2) -> Integer.max(v1, v2));
    }

    public static Optional<Book> getBookWithLongestTitleLength() {
        return books.stream().reduce((l, r) -> {
            if (l.getTitle().length() > r.getTitle().length()) {
                return l;
            } else {
                return r;
            }
        });
    }

    public static int findSumOfAllBookPages() {
        return books.stream().mapToInt(Book::getPages).sum();
    }

    public static OptionalInt findBiggestAmountOfPages() {
        return books.stream().mapToInt(Book::getPages)
                .max();
    }

    public static void findFirstInParallelStream() {
        books.parallelStream().map(Book::getPages)
                .findFirst()
                .ifPresent(o -> System.out.println("Any value is " + o));
    }

    public static void main(String[] args) {
        intStreams();
        stringStreams();
        printTitles();
        groupByAuthor();
        findTwoBooksFiltered();
        findUniqueCharacters();
        generateUniquePairs();
        demonstrateLoopFusionAndShortCircuiting();
        System.out.println("\nMax value is " + getMaximumValue().get() + "\n");
        getBookWithLongestTitleLength().ifPresent(o -> System.out.println("THe book with longest title: " + o));
        System.out.println("Sum of all book pages is " + findSumOfAllBookPages());
        findBiggestAmountOfPages().ifPresent(o -> System.out.println("The biggest amount of pages in the list is " + o));
        findFirstInParallelStream();
    }
}

class PairGenerator {

    public List<List<Integer>> generatePairs() {

        List<Integer> nums1 = Arrays.asList(1, 2, 3);
        List<Integer> nums2 = Arrays.asList(4, 5);

        return nums1.stream().flatMap(o -> nums2.stream().map(i -> List.of(o, i)))
                .toList();
    }
}