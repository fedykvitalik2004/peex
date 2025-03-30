package org.vitalii.fedyk.peex.dependecyinjection;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Library {
    private Book book;

    @Override
    public String toString() {
        return "Library has such a book:" + book;
    }
}
