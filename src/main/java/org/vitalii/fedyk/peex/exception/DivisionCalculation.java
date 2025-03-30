package org.vitalii.fedyk.peex.exception;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class DivisionCalculation {
    private final List<LocalDateTime> exceptionDates = new LinkedList<>();

    public int divideByZero(final int t1, final int t2) {
        try {
            return t1 / t2;
        } catch (ArithmeticException e) {
            exceptionDates.add(LocalDateTime.now());
            System.out.println("Date added");
            throw new IllegalArgumentException("You cannot divide by " + t2, e);
        } finally {
            System.out.println("Method is finished");
        }
    }

    public LocalDateTime getLastDate() {
        return exceptionDates.getLast();
    }

    public static void main(String[] args) {
        final DivisionCalculation divisionCalculation = new DivisionCalculation();
        try {
            System.out.println(divisionCalculation.divideByZero(34, 0));
        } catch (IllegalArgumentException e) {
            System.out.println("Message: " + e.getMessage());
            System.out.println(divisionCalculation.getLastDate());
        }
    }
}
