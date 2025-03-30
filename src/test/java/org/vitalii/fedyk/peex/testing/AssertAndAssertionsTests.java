package org.vitalii.fedyk.peex.testing;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class AssertAndAssertionsTests {
    @Test
    void testArrayIgnoringOrder() {
        final String[] strings1 = {"Hello", "World", "Java"};
        final String[] strings2 = {"Hello", "Java", "World"};

        assertArrayEquals(strings1, strings2);
    }

    @Test
    void testArrayExactOrder() {
        final String[] strings1 = {"Hello", "World", "Java"};
        final String[] strings2 = {"Hello", "Java", "World"};

        Arrays.sort(strings1);
        Arrays.sort(strings2);

        assertArrayEquals(strings1, strings2);
    }

    @Test
    void testArrayNotEqual() {
        final String[] strings1 = {"Hello", "Java", "World"};
        final String[] strings2 = {"Hello", "Java", "World"};

        assertNotEquals(strings1, strings2);
    }

    @Test
    void testNotAssignable() {
        Assert.isAssignable(String.class, Object.class);
    }

    @Test
    void testAssignable() {
        Assert.isAssignable(Object.class, String.class);
    }

    @Test
    void testIsInstanceOf() {
        Assert.isInstanceOf(String.class, "Hello");
        Assert.isInstanceOf(Object.class, "Hello");
    }

    @Test
    void testLength() {
        Assert.hasLength("Hello", "It should not be blank");
    }

    @Test
    void testAssertIterable() {
        final List<String> arrayList = new ArrayList();
        final List<String> linkedList = new LinkedList<>();

        for (int i = 0; i < 5; i++) {
            arrayList.add(String.valueOf(i));
            linkedList.add(String.valueOf(i));
        }

        assertIterableEquals(arrayList, linkedList);
    }

    @Test
    void testAssertIterableForNull() {
        final List<Car> arrayList = new ArrayList();
        final List<Car> linkedList = List.of(new Car(1, "1"));

        arrayList.add(new Car(98, "1"));

        assertIterableEquals(arrayList, linkedList);
    }

    @Test
    void testAssertLines() {
        final List<String> expected = List.of("Hello", "\\d+");
        final List<String> actual = List.of("Hello", "27");

        assertLinesMatch(expected, actual);
    }

    @Test
    void testAssertSame() {
        final String string1 = "Hello";
        final String string2 = string1;
        final String string3 = new String(string1);

        assertSame(string1, string2);
        assertNotSame(string2, string3);
    }

    @Test
    void testFail() {
        try {
            int i = 1 / 1;
            fail("Exception should be thrown");
        } catch (Exception e) {
        }
    }

    @Test
    void testAssertEquals() {
        final int initialValue = 5;
        final int finalValue = 100;
        final int delta = 95;

        assertEquals(initialValue, finalValue, delta);
    }

    @Test
    void testAssertTimeout() {
        assertTimeout(Duration.of(2, ChronoUnit.SECONDS), () -> Thread.sleep(1000));
        assertTimeout(Duration.of(1, ChronoUnit.SECONDS), () -> Thread.sleep(3000));
    }

    @Test
    void testAssertTimeoutPreemptively() {
        assertTimeoutPreemptively(Duration.of(500, ChronoUnit.MILLIS), () -> Thread.sleep(3000));
    }
}
