package org.vitalii.fedyk;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
class Book {
    private String name;
    private Author author;



    @AllArgsConstructor
    private class Author {
        private String firstName;
        private String lastName;


        @Override
        public String toString() {
            return "Author{" +
                   "firstName='" + firstName + '\'' +
                   ", lastName='" + lastName + '\'' +
                   '}';
        }
    }
}

public class NestedClassesUsage {
    public static void main(String[] args) {
        final List<Object> data = List.of("Hello", 4, 4.6);
//        final Storage storage = new Storage(data);
//        storage.
    }
}
