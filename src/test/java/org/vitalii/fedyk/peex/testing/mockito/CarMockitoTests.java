package org.vitalii.fedyk.peex.testing.mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.vitalii.fedyk.peex.testing.Car;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarMockitoTests {
    @Mock
    private Car mockedCar;

    @Test
    void testCarMethod() {
        final int value = 4;
        final int resultValue = 100;

        when(mockedCar.getSumOfAgeAndParameter(value))
                .thenReturn(resultValue);

        assertEquals(resultValue, mockedCar.getSumOfAgeAndParameter(value));

        verify(mockedCar, Mockito.times(1)).getSumOfAgeAndParameter(value);
    }
}
