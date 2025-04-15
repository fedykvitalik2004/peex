package org.vitalii.fedyk.peex.collections.list;


import java.util.Random;

public class ArrayExample {
  public static void main(String[] args) {
    final int[] array = new int[7];

    for (int i = 0; i < array.length; i++) {
      array[i] = new Random().nextInt(100) + 1;  // Between 1 and 100
    }

    for (int i = 0; i < array.length; i++) {
      System.out.println("Element at index " + i + ": " + array[i]);
    }
  }
}
