package org.vitalii.fedyk.peex;

public class Operations {
    //Arithmetical
    private static void printMathematicalOperators(final double a, final double b) {
        System.out.println("Sum is " + (a + b));
        System.out.println("Difference is " + (a - b));
        System.out.println("Product is " + (a * b));
        System.out.println("Quotient is " + (a / b));
        System.out.println("Remainder is " + (a % b));
    }

    private static void printComparisonOperators(final double a, final double b) {
        System.out.println("Equal to: " + (a == b));
        System.out.println("Not equal to: " + (a != b));
        System.out.println("Greater than: " + (a > b));
        System.out.println("Less than: " + (a < b));
        System.out.println("Greater than or equal to: " + (a >= b));
        System.out.println("Less than or equal to: " + (a <= b));
    }

    private static void printLogicalOperators(final boolean a, final boolean b) {
        System.out.println("Logical AND: " + (a && b));
        System.out.println("Logical OR: " + (a || b));
        System.out.println("Logical NOT (condition1): " + (!a));
        System.out.println("Logical NOT (condition2): " + (!b));
    }

    public static void printAssignmentOperators(final int a, final int b) {
        /*
            Here are all operations
         */
        int x = a;

        x += b;
        System.out.println("Addition assignment: " + x);

        x = a;
        x -= b;
        System.out.println("Subtraction assignment: " + x);

        x = a;
        x *= b;
        System.out.println("Multiplication assignment: " + x);

        x = a;
        x /= b;
        System.out.println("Division assignment: " + x);

        x = a;
        x %= b;
        System.out.println("Modulus assignment: " + x);

        x = a;
        x &= b;
        System.out.println("Bitwise AND assignment: " + x);

        x = a;
        x |= b;
        System.out.println("Bitwise OR assignment: " + x);

        x = a;
        x ^= b;
        System.out.println("Bitwise XOR assignment: " + x);

        x = a;
        x <<= b;
        System.out.println("Left shift assignment: " + x);

        x = a;
        x >>= 1;
        System.out.println("Right shift assignment: " + x);
    }

    private static void printIncrementAndDecrementOperators(final int a) {
        int b = a;
        System.out.println("++a = " + ++b);
        b = a;
        System.out.println("a++ = " + b++);
        b = a;
        System.out.println("a-- = " + b--);
        b = a;
        System.out.println("--a = " + --b);
    }

    public static void main(String[] args) {
        final double aDouble = 10;
        final double bDouble = 4;
        System.out.println("Double numbers a=%s and b=%s".formatted(aDouble, bDouble));
        printMathematicalOperators(aDouble, bDouble);
        System.out.println();

        printComparisonOperators(aDouble, bDouble);
        System.out.println();

        final boolean aBoolean = true;
        final boolean bBoolean = false;

        System.out.println("Booleans a=%s and b=%s".formatted(aBoolean, bBoolean));
        printLogicalOperators(aBoolean, bBoolean);
        System.out.println();

        int aInt = 10;
        int bInt = 2;
        System.out.println("Int numbers a=%s and b=%s".formatted(aInt, bInt));
        printAssignmentOperators(aInt, bInt);
        System.out.println();

        System.out.println("Int number a=%s".formatted(aInt));
        printIncrementAndDecrementOperators(aInt);
    }
}
