package org.vitalii.fedyk.peex.collections.streams;

import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileReading {
    @SneakyThrows
    public static void main(String[] args) {
        final String path = "/Users/fedyk_vitalii/Desktop/karate-project" +
                            "/peex/src/main/resources/names.txt";
        final Stream<String> stream =  Files.lines(Paths.get(path));
        stream.forEach(System.out::println);
    }
}
