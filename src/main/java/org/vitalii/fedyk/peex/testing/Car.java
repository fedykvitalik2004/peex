package org.vitalii.fedyk.peex.testing;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "cars")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int age;
    private String name;

    public Car(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getSumOfAgeAndParameter(final int parameter) {
        return age + parameter;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(name, car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
