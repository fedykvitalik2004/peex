package org.vitalii.fedyk.peex.collections.map;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class LinkedHashMapExample {
    public static void main(String[] args) {
        final Map<String, Integer> map = new LinkedHashMap<>();
        map.put("Anton", 45);
        map.put("Bohdan", 20);
        map.put("Carl", 34);
        map.put("Daryna", 16);

        final Set<String> set = map.keySet();
        for (String key: set) {
            System.out.println(key + " - " + map.get(key));
        }
    }
}
