package org.vitalii.fedyk.peex.exception;

import lombok.AllArgsConstructor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@AllArgsConstructor
public class ReadingFromFile {
    public static void copy(final String from, final String to) {
        final File file = new File(to);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("File doesn't exist and it cannot be created ");
                return;
            }
        }
        try(final FileInputStream input = new FileInputStream(from); final FileOutputStream output = new FileOutputStream(to)) {
            final byte[] array = input.readAllBytes();
            final String content = new String(array);
            System.out.println("Content: \n" + content);

            output.write(array);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        final String from = "src/main/resources/names.txt";
        final String to = "src/main/resources/copy_of_names.txt";
        copy(from, to);

        final String nonExistentFrom = "non-existing";
        copy(nonExistentFrom, to);
    }
}
