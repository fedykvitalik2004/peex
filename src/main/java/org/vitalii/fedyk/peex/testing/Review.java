package org.vitalii.fedyk.peex.testing;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reviews")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int stars;
    @OneToOne
    @JoinColumn(name = "car_id")
    private Car car;
}
