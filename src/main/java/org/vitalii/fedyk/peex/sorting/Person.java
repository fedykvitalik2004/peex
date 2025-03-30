package org.vitalii.fedyk.peex.sorting;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Person implements Comparable<Person> {
    private int age;

    @Override
    public int compareTo(Person o) {
        return Integer.compare(age, o.getAge());
    }
}
