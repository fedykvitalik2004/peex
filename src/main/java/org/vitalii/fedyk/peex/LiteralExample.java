package org.vitalii.fedyk.peex;

public class LiteralExample {
    public static void main(String[] args) {
        //Integers
        final int binary = 0b101;
        System.out.println("Binary 0b101: " + binary);
        final int octal = 017;
        System.out.println("Decimal 017: " + octal);
        final int decimal = 30;
        System.out.println("Decimal 30: " + decimal);
        final int hexagonal = 0xa3;
        System.out.println("Hexagonal 0xa3: " + hexagonal);
        final long longNumber = 130L;
        System.out.println("Long 130L: " + longNumber);
        //Floats
        final float floatNumber = 3.53f;
        System.out.println("\nFloat 3.53f: " + floatNumber);
        final double doubleNumber = 352.53;
        System.out.println("Double 352.53: " + doubleNumber);
        final double doubleNumberWithPostfix = 352.53d;
        System.out.println("Double 352.53d: " + doubleNumberWithPostfix);
        final double scientificDouble = 35253e-2;
        System.out.println("Scientific double 3525.3e-2: " + scientificDouble);
        //Characters
        final char simpleChar = 'j';
        final char unicodeChar = '\u0035';
        final char numberRepresentationChar = (char) 100;
        final char tabulationChar = '\t';
        System.out.println("\nCombination of chars: " + simpleChar + tabulationChar + unicodeChar + numberRepresentationChar);
        //Strings
        final String string = "Old style";
        final String newStringRepresentation = """
                Hello
                World
                """;
        System.out.println("\nString in old style: " + string);
        System.out.println("New string representation: '" + newStringRepresentation + "'");
        //Booleans
        final boolean positiveValue = true;
        final boolean negativeValue = false;
        System.out.println("\nBooleans are " + positiveValue + " and " + negativeValue);
        //Null
        System.out.println("\nNull literal is " + null);
    }
}
