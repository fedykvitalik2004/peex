package org.vitalii.fedyk.peex.testing.mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.vitalii.fedyk.peex.testing.Dog;
import org.vitalii.fedyk.peex.testing.Toy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ToySpyTests {
    @Spy
    private Toy toy;
    @InjectMocks
    private Dog dog;
    @Captor
    private ArgumentCaptor<String> captor;

    @Test
    void testGetToyNameWithoutStubbing() {
        final String result = dog.getToyName();
        assertEquals("Toy", result);

        verify(toy).getName();
    }

    @Test
    void testGetToyWithStubbing() {
        final String newName = "Dog Toy";
        when(toy.getName()).thenReturn(newName);

        final String result = dog.getToyName();
        assertEquals(newName, result);

        verify(toy).getName();
    }

    @Test
    void testGetToyWithUsingArgumentCaptor() {
        dog.sayHello();

        verify(toy).say(captor.capture());
        assertEquals("Hello", captor.getValue());
    }
}

class FirstWayOfUsingMockito {
    @Test
    void testGetToy() {
        final Toy toy = Mockito.mock(Toy.class);
        final Dog dog = new Dog(toy);

        when(toy.getName()).thenReturn("Toy");

        final String result = dog.getToyName();
        assertEquals("Toy", result);
    }
}

@SpringBootTest
class SecondWayOfUsingMockito {
    @MockitoBean
    private Toy toy;
    @Autowired
    private Dog dog;

    @Test
    void testGetToy() {
        when(toy.getName()).thenReturn("Toy");

        final String result = dog.getToyName();

        assertEquals("Toy", result);
    }
}