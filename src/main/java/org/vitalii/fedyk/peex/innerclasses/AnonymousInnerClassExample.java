package org.vitalii.fedyk.peex.innerclasses;

import java.util.concurrent.atomic.AtomicInteger;

public class AnonymousInnerClassExample {
    private final static AtomicInteger atomicInteger = new AtomicInteger();

    private static Runnable getRunnable (final int counter) {
        return new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < counter; i++) {
                    atomicInteger.incrementAndGet();
                }
            }
        };
    }

    public static void main(String[] args) {
        final Thread thread1 = new Thread(getRunnable(1000));
        final Thread thread2 = new Thread(getRunnable(1000));

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Integer value: " + atomicInteger.get());
    }
}
