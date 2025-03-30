package org.vitalii.fedyk.peex.collections.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PersonHashMap {
    public static void main(String[] args) {
        final Map<Person, Integer> personIntegerMap = new HashMap<>();

        personIntegerMap.put(new Person("Vitalii", 20), 1);
        personIntegerMap.put(new Person("Vitalii", 11), 5);
        personIntegerMap.put(new Person("Andrii", 20), 5);

        final Set<Person> keys = personIntegerMap.keySet();
        System.out.println("Keys amount is " + keys.size());
    }
}
