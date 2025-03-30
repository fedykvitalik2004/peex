package org.vitalii.fedyk.peex.testing.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.vitalii.fedyk.peex.testing.dto.CarDto;
import org.vitalii.fedyk.peex.testing.repository.CarRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CarServiceTests {
    @Autowired
    private CarService carService;
    @Autowired
    private CarRepository carRepository;

    @Test
    void testSave() {
        carService.createCar(CarDto.builder()
                .age(10)
                .name("BMW")
                .build());

        assertEquals(1, carRepository.count());
    }

    @Test
    void testGetAllCars() {

    }

}

