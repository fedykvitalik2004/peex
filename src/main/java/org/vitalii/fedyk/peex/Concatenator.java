package org.vitalii.fedyk.peex;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class Concatenator {
    private static String concat(final Delimiter delimiter, final String... strings) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            stringBuilder.append(strings[i]);
            if (i != strings.length - 1) {
                stringBuilder.append(delimiter.getString());
            }
        }
        return stringBuilder.toString();
    }

    public static String concat(final String... strings) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (String string : strings) {
            stringBuilder.append(string);
        }
        return stringBuilder.toString();
    }

    @AllArgsConstructor
    @Getter
    public enum Delimiter {
        EMPTY(""),
        ONE_WHITESPACE(" ");

        private final String string;
    }

    public static void main(String[] args) {
        final String[] emptyArray = {};
        System.out.println("String from empty array: " + concat(Delimiter.ONE_WHITESPACE, emptyArray));
        System.out.println("String from nothing (no arguments): " + concat(Delimiter.ONE_WHITESPACE));
        System.out.println("String from several arguments: " + concat(Delimiter.EMPTY, "Hello", " ", "World"));
        System.out.println("String from array: " + concat(Delimiter.ONE_WHITESPACE, "Today", "is", "an awesome", "day"));

        System.out.println("\nString from array, using another overloaded method: " + concat("Overloading", " used"));
    }
}
