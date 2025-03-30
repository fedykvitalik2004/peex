package org.vitalii.fedyk.peex.collections.generics;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Pair<K, V> {
    private K key;
    private V value;

    @Override
    public String toString() {
        return "Pair: " + key + " - " + value;
    }

    public static void main(String[] args) {
        final Pair<Integer, String> pair = new Pair<>(1, "One");
        System.out.println(pair);
    }
}
