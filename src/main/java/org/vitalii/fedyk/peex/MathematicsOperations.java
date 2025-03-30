package org.vitalii.fedyk.peex;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Random;

public class MathematicsOperations {
    private final static Random random = new Random();

    private static int generateIntegerRandomNumber(final int min, final int max) {
        return random.nextInt(max - min + 1) + min;
    }

    private static double calculateSin(final double angle) {
        final double value = Math.sin(Math.toRadians(angle));
        return Math.abs(value) < 1e-10 ? 0.0 : value;
    }

    private static double calculateCos(final double degrees) {
        final double value = Math.cos(Math.toRadians(degrees));
        return Math.abs(value) < 1e-10 ? 0.0 : value;
    }

    private static double calculateTan(final double degrees) {
        final double value = Math.tan(Math.toRadians(degrees));
        return Math.abs(value) < 1e-10 ? 0.0 : value;
    }

    private static double calculateExponential(final double x) {
        return Math.exp(x);
    }

    private static double calculateLogarithm10(final double x) {
        return Math.log10(x);
    }

    public static void defineEvenAndOdd(final int num) {
        if (num % 2 == 0) {
            System.out.println(num + " is an even number");
        } else {
            System.out.println(num + " is an odd number");
        }
    }

    public static boolean isLeapYear(final int year) {
        return year % 4 == 0;
    }

    private static void displayRoundedNumbers(final int scale, final double value) {
        System.out.println("Rounding using FLOOR: " + BigDecimal.valueOf(value).setScale(scale, RoundingMode.FLOOR));
        System.out.println("Rounding using CEILING: " + BigDecimal.valueOf(value).setScale(scale, RoundingMode.CEILING));
        System.out.println("Rounding using UP: " + BigDecimal.valueOf(value).setScale(scale, RoundingMode.UP));
        System.out.println("Rounding using DOWN: " + BigDecimal.valueOf(value).setScale(scale, RoundingMode.DOWN));
        System.out.println("Rounding using HALF_UP: " + BigDecimal.valueOf(value).setScale(scale, RoundingMode.HALF_UP));
        System.out.println("Rounding using HALF_EVEN: " + BigDecimal.valueOf(value).setScale(scale, RoundingMode.HALF_EVEN));
        System.out.println("Rounding using HALF_DOWN: " + BigDecimal.valueOf(value).setScale(scale, RoundingMode.HALF_DOWN));
        try {
            System.out.println("Rounding using UNNECESSARY: " + BigDecimal.valueOf(value).setScale(scale, RoundingMode.UNNECESSARY));
        } catch (final Exception e) {
            System.out.println("Rounding using UNNECESSARY failed");
        }
    }

    private static void executeSomeTrigonometricFunctions(final double degrees) {
        System.out.println("Sinus of " + degrees + ": " + calculateSin(degrees));
        System.out.println("Cosinus of " + degrees + ": " + calculateCos(degrees));
        System.out.println("Tangent of " + degrees + ": " + calculateTan(degrees));
    }

    private static <T> void cyclicDisplayingArrayElements(final T[] array, final int quantity) {
        for (int i = 0; i < quantity; i++) {
            System.out.println(i + " - " + array[i % array.length]);
        }
    }

    private static <T> boolean checkAllOccurrenceViaStep(final T[] array, final int step) {
        for (int i = 0; i + step < array.length; i+=step) {
            if (array[i] != array[i + step]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        final int initialNumber = 9;
        final int squareRoot = (int) Math.sqrt(initialNumber);
        final int power = (int) Math.pow(initialNumber, 2);

        System.out.println("Initial number: " + initialNumber);
        System.out.println("Square root: " + squareRoot);
        System.out.println("Power of 2: " + power);
        //------------------------------------------------------------
        System.out.println("\n\nRandom number from -2 to 2: " + generateIntegerRandomNumber(-3, 1));
        System.out.println("Random number from 0 to 4: " + generateIntegerRandomNumber(0, 4));
        //------------------------------------------------------------
        double valueToRound = 3.635582;
        int scale = 3;
        System.out.println("\n\nDisplaying rounded numbers (scale " + scale + ") via BigDecimal class for value " + valueToRound);
        displayRoundedNumbers(3, valueToRound);

        valueToRound = -35.32;
        scale = 0;
        System.out.println("\n\nDisplaying rounded numbers (scale " + scale + ") via BigDecimal class for value " + valueToRound);
        displayRoundedNumbers(scale, valueToRound);
        //------------------------------------------------------------
        System.out.println("\n\nOperating on arbitrary-precision decimal numbers");
        final BigDecimal firstBigDecimal = new BigDecimal("15.00000000000000000000000000004");
        final BigDecimal secondBigDecimal = new BigDecimal("13.00000000000000000000000000002");
        System.out.println("First BigDecimal is " + firstBigDecimal);
        System.out.println("Second BigDecimal is " + secondBigDecimal);

        System.out.println("Sum: " + firstBigDecimal.add(secondBigDecimal));
        System.out.println("Subtraction: " + firstBigDecimal.subtract(secondBigDecimal));
        System.out.println("Multiplication: " + firstBigDecimal.multiply(secondBigDecimal));
        System.out.println("Division: " + firstBigDecimal.divide(secondBigDecimal, RoundingMode.HALF_UP));
        //------------------------------------------------------------
        System.out.println("\n\nTrigonometric functions (sin, cos, tan) for 180 degrees: ");
        executeSomeTrigonometricFunctions(180);
        System.out.println("\n\nTrigonometric functions (sin, cos, tan) for 45 degrees: ");
        executeSomeTrigonometricFunctions(60);
        //------------------------------------------------------------
        System.out.println("\n\nExponential of 3: " + calculateExponential(3));
        //------------------------------------------------------------
        System.out.println("\n\nLogarithm base 10 of 100: " + calculateLogarithm10(100));
        //------------------------------------------------------------
        /*
            Example:
            floorMod(23, 4): First, calculate 23 divided by 4, which is 5.75. The floor of 5.75 is 5. Then, calculate 23 - (5 * 4) = 23 - 20 = 3.
            floorMod(-23, 4): Calculate -23 divided by 4, which is -5.75. The floor of -5.75 is -6. Then, calculate -23 - (-6 * 4) = -23 + 24 = 1.
            floorMod(23, -4): Calculate 23 divided by -4, which is -5.75. The floor of -5.75 is -6. Then, calculate 23 - (-6 * -4) = 23 - 24 = -1.
            floorMod(-23, -4): Calculate -23 divided by -4, which is 5.75. The floor of 5.75 is 5. Then, calculate -23 - (5 * -4) = -23 - (-20) = -23 + 20 = -3.
         */
        System.out.println("\n\nFloorMod function " + Math.floorMod(10, 4));
        /*
            Example:
            ceilMod(23, 4): First, calculate 23 divided by 4, which is 5.75. The ceiling of 5.75 is 6. Then, calculate 23 - (6 * 4) = 23 - 24 = -1.
            ceilMod(-23, 4): Calculate -23 divided by 4, which is -5.75. The ceiling of -5.75 is -5. Then, calculate -23 - (-5 * 4) = -23 + 20 = -3.
            ceilMod(23, -4): Calculate 23 divided by -4, which is -5.75. The ceiling of -5.75 is -5. Then, calculate 23 - (-5 * -4) = 23 - 20 = 3.
            ceilMod(-23, -4): Calculate -23 divided by -4, which is 5.75. The ceiling of 5.75 is 6. Then, calculate -23 - (6 * -4) = -23 + 24 = 1.
         */
        System.out.println("CeilMod function " + Math.ceilMod(-23, 4));
        //------------------------------------------------------------
        System.out.println("\n\nDealing with mod operator");
        defineEvenAndOdd(4);
        defineEvenAndOdd(3);
        int year = 2004;
        boolean isLeap = isLeapYear(year);
        System.out.println("Year " + year + " " + (isLeap ? "leap" : "not leap"));
        year = 2003;
        isLeap = isLeapYear(year);
        System.out.println("Year " + year + " " + (isLeap ? "leap" : "not leap"));
        final Integer[] array = {1, 2, 3, 4};
        System.out.println("Given such an array: " + Arrays.toString(array));
        final int to = 10;
        cyclicDisplayingArrayElements(array, to);
        System.out.println("6 mod 5 is " + (6 % 5));
        System.out.println("5 mod 6 is " + (5 % 6));
        final String[] letters = {"a", "b", "c", "a", "d", "g", "a"};
        System.out.println("Letters are " + Arrays.toString(letters));
        System.out.println("Checking all occurrences of 'a' via step 3: " + checkAllOccurrenceViaStep(letters, 3));
    }
}
