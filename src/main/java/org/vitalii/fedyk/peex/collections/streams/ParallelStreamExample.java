package org.vitalii.fedyk.peex.collections.streams;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class ParallelStreamExample {
    public static boolean isPrime(long number) {
        if (number <= 1) return false;
        if (number == 2) return true;
        if (number % 2 == 0) return false;

        final long maxDivisor = (long) Math.sqrt(number);

        for (int i = 3; i < maxDivisor; i+=2) {
            if (number % i == 0) return false;
        }

        return true;
    }

    public static long sum(long n) {
        return LongStream.rangeClosed(1, n)
                .reduce(0, Long::sum);
    }

    public static long parallelSum(long n) {
        return LongStream.rangeClosed(1, n)
                .parallel()
                .reduce(0, Long::sum);
    }

    public static void main(String[] args) {
        long before = System.currentTimeMillis();
        long count = IntStream.rangeClosed(1, Integer.MAX_VALUE / 100)
                .filter(ParallelStreamExample::isPrime).count();
        long after = System.currentTimeMillis();

        System.out.println("Time taken is " + (after - before) + " and the result is " + count);

        before = System.currentTimeMillis();
        count = IntStream.rangeClosed(1, Integer.MAX_VALUE / 100).parallel()
                .filter(ParallelStreamExample::isPrime).count();
        after = System.currentTimeMillis();

        System.out.println("Time taken is " + (after - before) + " and the result is " + count);

        before = System.currentTimeMillis();
        count = sum(900000000);
        after = System.currentTimeMillis();

        System.out.println("Sum from 1 to 100000 is " + count + ". Time taken: " + (after - before));

        before = System.currentTimeMillis();
        count = parallelSum(900000000);
        after = System.currentTimeMillis();

        System.out.println("Sum from 1 to 100000 is " + count + ". Time taken: " + (after - before));

    }
}
