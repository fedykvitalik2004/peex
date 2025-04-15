package org.vitalii.fedyk.peex;

public class StringExample {
    private static String reverseString(final String string) {
        final StringBuilder stringBuilder = new StringBuilder(string);
        return stringBuilder.reverse().toString();
    }

    public static void main(String[] args) {
        final char[] chars = "Hello World".toCharArray();
        final String string = new String(chars);

        System.out.println("Basic string is " + string);
        System.out.println("Length of string: " + string.length());
        System.out.println("First character is " + string.charAt(0));
        System.out.println("Uppercase: " + string.toUpperCase());
        System.out.println("Replaced string: " + string.replace("World", "people"));
        System.out.println("If this string contains ' ': " + string.contains(" "));
        System.out.println("Concatenated string: " + string.concat("!"));
        System.out.println("Substring from 2 to 5: " + string.substring(2, 5));
        System.out.println("Reversed string: " + reverseString(string));


    }
}
