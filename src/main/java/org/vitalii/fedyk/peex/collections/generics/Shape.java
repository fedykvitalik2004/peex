package org.vitalii.fedyk.peex.collections.generics;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public interface Shape {
    void draw();
}

class Main {
    public static void drawAll(List<? extends Shape> list) {
        System.out.println("First way");
        for (Shape shape : list) {
            shape.draw();
        }
    }

    public static <T extends Shape> void drawAllInAnotherWay(List<T> list) {
        System.out.println("Second way");
        for (Shape shape : list) {
            shape.draw();
        }
    }

    public static boolean addElement(final List<? super Shape> list) {
        return list.add(new Rectangle());
    }

    public static void printObjects(List<? super Shape> list) {
        for (Object o : list) {
            System.out.println(o);
        }
    }

    public static void printSuperNumbers(List<? super Number> list) {
        for (Object object : list) {
            System.out.println(object);
        }
    }

    public static <T> void copy(List<? extends T> source, List <? super T> destination) {
        for (T t: source) {
            destination.add(t);
        }
    }

    public static void main(String[] args) {
        List<Circle> circles = List.of(new Circle());
        drawAll(circles);
        drawAllInAnotherWay(circles);

        System.out.println();
        List<Shape> rectangles = new ArrayList<>();
        addElement(rectangles);
        drawAll(rectangles);

        System.out.println("\nPrinting Shapes");
        printObjects(rectangles);

        System.out.println("\nPrinting strings");
        final List<Serializable> serializables = new ArrayList<>();
        serializables.add("Hello");
        serializables.add("World");
        printSuperNumbers(serializables);


        List<? extends String> list = new ArrayList<>();
        list.add(null);
    }
}
