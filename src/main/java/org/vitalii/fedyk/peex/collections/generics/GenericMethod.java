package org.vitalii.fedyk.peex.collections.generics;

public class GenericMethod {
    public <T> void showObject(final T t) {
        System.out.println("Here is the object: " + t);
    }

    public <T> void showArray(final T[] array) {
        for (T t: array) {
            System.out.println("Element is " + t);
        }
    }

    public static void main(String[] args) {
        Integer[] array = {1, -5, 3};

        final GenericMethod genericMethod = new GenericMethod();
        genericMethod.showObject(26);
        genericMethod.showArray(array);
    }
}
