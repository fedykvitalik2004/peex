package org.vitalii.fedyk.peex.testing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CarTests {
    @Test
    void testGetSumOfAgeAndParameter() {
        final int expected = 45;
        final Car car = new Car(30, "BMW");

        final int result = car.getSumOfAgeAndParameter(15);

        assertEquals(expected, result);
    }

    @Test
    void testEquals() {
        final Car car1 = new Car(30, "BMW");
        final Car car2 = new Car(100, "BMW");
        final Car car3 = new Car(30, "Audi");

        assertEquals(car1, car2, "Cars with the same brands should be equal");
        assertNotEquals(car1, car3, "Cars with the same brands should be equal, but not age");
    }
}
