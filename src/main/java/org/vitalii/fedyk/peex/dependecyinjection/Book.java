package org.vitalii.fedyk.peex.dependecyinjection;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class Book {
    private String name;
    private String author;

    @Override
    public String toString() {
        return "'" + name + "' by " + author;
    }
}
