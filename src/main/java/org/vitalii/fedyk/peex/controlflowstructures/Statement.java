package org.vitalii.fedyk.peex.controlflowstructures;

import java.util.List;

class Car {
    private boolean isTurnedOn;

    public void toggle() {
        isTurnedOn = !isTurnedOn;
    }

    public void go() {
        if (isTurnedOn) {
            System.out.println("Car is moving");
        } else {
            System.out.println("Car is not moving");
        }
    }

    public static void main(String[] args) {
        final Car car = new Car();
        car.go();
        car.toggle();
        car.go();
    }
}

class WhileUsage {
    public static void main(String[] args) {
        int initialValue = 0;
        final int value = 10;

        while (initialValue < value) {
            System.out.println(initialValue);
            initialValue++;
        }
    }
}

class DoWhileUsage {
    public static void main(String[] args) {
        do {
            System.out.println("Printed once");
        } while (false);
    }
}

class ForUsage {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i += 2) {
            System.out.println(i);
        }
    }
}

class EnhancedForUsage {
    public static void main(String[] args) {
        final int[] numbers = {0, 1, 2, 3, 4};
        for (int i : numbers) {
            System.out.println(i);
        }

        final List<Integer> list = List.of(5, 6, 7, 8, 9);
        for (Integer i : list) {
            System.out.println(i);
        }
    }
}

class BranchingStrategy {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            if (i == 3) {
                System.out.println("The loop is gonna finish now: i = " + i);
                break;
            }
        }
    }
}

class SwitchExample {
    public static void byteExample(final byte value) {
        switch (value) {
            case 1:
                System.out.println("Value is 1");
                break;
            case 2:
                System.out.println("Value is 2");
                break;
            default:
                System.out.println("Value is neither 1 nor 2");
                break;
        }
    }

    public static void shortExample(final short value) {
        switch (value) {
            case (1 + 1):
                System.out.println("Value is 2");
                break;
            default:
                System.out.println("Value is not 1");
                break;
        }
    }

    public static String getSeasonName(final int month) {
        //Old style
        String seasonName;
        switch (month) {
            case 1:
                seasonName = "Winter";
                break;
            case 2:
                seasonName = "Spring";
                break;
            case 3:
                seasonName = "Summer";
                break;
            case 4:
                seasonName = "Autumn";
                break;
            default:
                seasonName = "Invalid month";
                break;
        }
        return seasonName;
    }

    public static void main(String[] args) {
        byteExample((byte) 4);
        shortExample((short) 2);
        System.out.println("Today is " + getSeasonName(1));
    }
}

class SwitchFall {
    public static void main(String[] args) {
        final int month = 2;
        switch (month) {
            case 1:
                System.out.println("Hello");
            case 2:
                System.out.println("Hola");
            case 3:
                System.out.println("Hi");
            default:
                System.out.println("Hey");
        }
    }
}

class UnlabelledBreak {
    public static void main(String[] args) {

        int[][] arrayOfInts = {
                { -1, 4, 5},
                { -9, 100, 2},
                { 90, 42, 77}
        };
        int value = -9;

        int i;
        int j = Integer.MIN_VALUE;
        boolean foundIt = false;

        unlabeledSwitchCase:
            for (i = 0; i < arrayOfInts.length; i++) {
                for (j = 0; j < arrayOfInts[i].length; j++) {
                    System.out.println("+");
                    if (arrayOfInts[i][j] == value) {
                        foundIt = true;
                        break unlabeledSwitchCase;
                    }
                }
            }

        if (foundIt) {
            System.out.println("Found " + value + " at " + i + ", " + j);
        } else {
            System.out.println(value + " not in the array");
        }
    }
}

class ContinueUsage {
    public static void main(String[] args) {
        String value = "Hello Hello";
        int counter = 0;
        for (char c : value.toCharArray()) {
            if (c!='l') {
                continue;
            }
            counter+=1;
        }
        System.out.println(counter);
    }
}

class LabeledContinue {
    public static void main(String[] args) {
        outerLoop:
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                if (j == 1) {
                    continue outerLoop; //Skips the current iteration of the outer loop
                }
                System.out.println("i = " + i + ", j = " + j);
            }
        }
    }
}