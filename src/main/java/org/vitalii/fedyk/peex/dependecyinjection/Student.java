package org.vitalii.fedyk.peex.dependecyinjection;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Student {
    private Book book;

    @Override
    public String toString() {
        return "The user reads such a book " + book;
    }
}
