package org.vitalii.fedyk.peex.collections.streams;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class Book {
    private String author;
    private String title;
    private int pages;
}
