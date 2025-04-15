package org.vitalii.fedyk.peex.innerclasses;

public class LocalInnerExample {
    public static void main(String[] args) {
        //Final or effective final is supposed to be passed
        class Printer {
            private final int number;

            public Printer(int number) {
                this.number = number;
            }

            public void display() {
                System.out.println("The number is " + number);
            }
        }

        final Printer printer = new Printer(4);
        printer.display();
    }
}
