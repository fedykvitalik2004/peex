package org.vitalii.fedyk.peex.testing.repository;

import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.vitalii.fedyk.peex.testing.Car;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class CarRepositoryTests {
    @Autowired
    private CarRepository carRepository;
    private final Faker faker = new Faker();

    @Test
    void testSave() {
        //Arrange
        final Car car = new Car(faker.number().numberBetween(1, 15), faker.name().name());

        //Act
        final Car savedCar = carRepository.save(car);

        //Assert
        assertNotNull(savedCar);
        assertThat(savedCar.getId()).isPositive();
    }

    @Test
    void testGetAll() {
        final List<Car> carList = List.of(
                new Car(faker.number().numberBetween(1, 20), faker.name().name()),
                new Car(faker.number().numberBetween(1, 15), faker.name().name())
        );

        carRepository.saveAll(carList);
        final long currentAmount = carRepository.findAll().size();

        assertEquals(carList.size(), currentAmount);
    }

    @Test
    void testFindById() {
        final Car car = new Car(faker.number().numberBetween(0, 10), faker.name().name());

        carRepository.save(car);

        final Optional<Car> retrievedCar = carRepository.findById(car.getId());

        assertThat(retrievedCar).isNotEmpty();
        assertThat(retrievedCar.get().getId()).isNotNull();
    }

    @Test
    void findByAge() {
        final List<Car> carList = List.of(
                new Car(4, "BMW"),
                new Car(6, "Mercedes"),
                new Car(10, "Audi"),
                new Car(10, "BMW")
        );

        carRepository.saveAll(carList);

        final int actualSize = carList.stream().filter(o -> o.getAge() == 10).toList().size();
        final int result = carRepository.findByAge(10).size();

        assertThat(result).isEqualTo(actualSize);
    }

    @Test
    void testUpdate() {
        final int ageBefore = faker.number().numberBetween(0, 30);
        final Car car = new Car(ageBefore, faker.company().name());

        carRepository.save(car);

        final Car retrievedCar = carRepository.findById(car.getId()).get();
        retrievedCar.setAge(faker.number().numberBetween(30, 60));

        carRepository.save(retrievedCar);

        final Optional<Car> updatedCar = carRepository.findById(car.getId());

        assertThat(updatedCar).isNotEmpty();
        assertNotEquals(updatedCar.get().getAge(), ageBefore);
    }

    @Test
    void testDelete() {
        final Car car = new Car(faker.number().positive(), "Audi");

        carRepository.save(car);

        carRepository.deleteById(car.getId());
        Optional<Car> retrievedCar = carRepository.findById(car.getId());

        assertThat(retrievedCar).isEmpty();
    }
}
