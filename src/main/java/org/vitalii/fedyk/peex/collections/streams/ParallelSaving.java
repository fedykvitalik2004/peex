package org.vitalii.fedyk.peex.collections.streams;

import lombok.SneakyThrows;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class ParallelSaving {
    private static final String DIRECTORY = System.getProperty("user.dir") + "/files/";

    @SneakyThrows
    public static void main(String[] args) {
        Files.createDirectory(Paths.get(DIRECTORY));

        final List<Person> people = generatePeople(100000);

        final long before = System.currentTimeMillis();
        people.stream().parallel().forEach(ParallelSaving::save);
        final long after = System.currentTimeMillis();
        System.out.println("Time taken is " + (after - before));
    }

    private static void save(Person person) {
        try (FileOutputStream stream = new FileOutputStream(
                new File(DIRECTORY + person.getPersonId() + ".txt"))) {
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private static List<Person> generatePeople(int n) {
        return Stream.iterate(0, o -> o + 1)
                .limit(n)
                .map(Person::new)
                .toList();
    }
}
