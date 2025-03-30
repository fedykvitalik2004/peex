package org.vitalii.fedyk.peex.testing.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.vitalii.fedyk.peex.testing.Car;
import org.vitalii.fedyk.peex.testing.dto.CarDto;
import org.vitalii.fedyk.peex.testing.dto.PageResponse;
import org.vitalii.fedyk.peex.testing.repository.CarRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class CarService {
    private CarRepository carRepository;

    public CarDto createCar(CarDto carDto) {
        final Car car = Car.builder()
                .name(carDto.getName())
                .age(carDto.getAge())
                .build();

        final Car savedCar = carRepository.save(car);

        return CarDto.builder()
                .id(savedCar.getId())
                .name(savedCar.getName())
                .age(savedCar.getAge())
                .build();
    }

    public CarDto updateCar(CarDto carDto) {
        final Car car = carRepository.findById(carDto.getId())
                .orElseThrow(NoSuchElementException::new);

        car.setName(carDto.getName());
        car.setAge(carDto.getAge());

        final Car savedCar = carRepository.save(car);
        return CarDto.builder()
                .name(savedCar.getName())
                .age(car.getAge())
                .build();
    }

    public PageResponse<CarDto> getAllCars(int pageNumber, int pageSize) {
        final Pageable pageable = PageRequest.of(pageNumber, pageSize);
        final Page<Car> page = carRepository.findAll(pageable);

        final List<CarDto> content = page.getContent().stream()
                .map(car -> CarDto.builder()
                        .id(car.getId())
                        .name(car.getName())
                        .age(car.getAge())
                        .build())
                .toList();

        return PageResponse.<CarDto>builder()
                .pageNumber(page.getNumber())
                .pageSize(page.getSize())
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .last(page.isLast())
                .content(content)
                .build();
    }

    public void deleteById(final long id) {
        carRepository.delete(carRepository.findById(id)
                .orElseThrow(NoSuchElementException::new));
    }
}
