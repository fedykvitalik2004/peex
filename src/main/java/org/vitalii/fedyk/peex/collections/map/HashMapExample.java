package org.vitalii.fedyk.peex.collections.map;

import ch.qos.logback.core.encoder.JsonEscapeUtil;

import java.util.HashMap;
import java.util.Map;

public class HashMapExample {
    public static void main(String[] args) {
        final Map<Integer, String> map = new HashMap<>();
        //O(1)
        map.put(1, "Adam");
        map.put(45, "Kate");
        map.put(90, "Max");
        map.put(null, "Vlad");
        //O(1)
        System.out.println("Key 45: " + map.get(45));
        System.out.println("Key 100 which is not present: " + map.get(100));
        System.out.println("Key null: " + map.get(null));

        System.out.println("Removed by null: " + map.remove(null));

        System.out.println("\nAll items:");
        for (Map.Entry<Integer, String> entry: map.entrySet()) {
            System.out.println(entry.getKey() + " and " + entry.getValue());
        }
    }
}
