package org.vitalii.fedyk.peex;

public class Casting {
    public static void main(String[] args) {
        byte byteValue = 45;
        short shortValue = 25932;
        int intValue = 385839;
        long longValue = 1000000000L;

        float floatValue = 4543;
        double doubleValue = 4838993;

        char charValue = 100;
        //Implicit conversion
        shortValue = byteValue;
        intValue = shortValue;
        //Allowed
//        intValue = charValue;
        longValue = intValue;
        floatValue = longValue;
        doubleValue = floatValue;
        System.out.println("Implicit conversion for double: " + doubleValue);
        //Explicit conversion
        doubleValue = 4.0;
        floatValue = (float) doubleValue;
        longValue = (long) floatValue;
        intValue = (int) longValue;
        shortValue = (short) intValue;
        byteValue = (byte) shortValue;
        System.out.println("Explicit conversion for byte: " + byteValue);

        System.out.println("Converting from int to String using String method: " + String.valueOf(intValue));
        System.out.println("Converting from double to String: " + Double.toString(doubleValue));
        System.out.println("Converting from String to double: " + Double.parseDouble("1452.6")); //valueOf can be used too

        final Integer integer = 4; //boxing
        final int i = integer; //unboxing
        //Wrapper cross-type conversion
        final Short shortWrapper = integer.shortValue();
    }
}
