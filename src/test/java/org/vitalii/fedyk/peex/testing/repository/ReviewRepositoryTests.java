package org.vitalii.fedyk.peex.testing.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.vitalii.fedyk.peex.testing.Car;
import org.vitalii.fedyk.peex.testing.Review;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class ReviewRepositoryTests {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private CarRepository carRepository;

    @Test
    void testSave() {
        final Car car = carRepository.save(Car.builder()
                .age(2)
                .name("Mazda")
                .build()
        );

        final Review review = Review.builder()
                .stars(5)
                .car(car)
                .build();

        reviewRepository.save(review);

        final Review retrievedReview = reviewRepository.findById(review.getId()).get();

        assertNotNull(retrievedReview);
        assertNotNull(retrievedReview.getCar());
        assertThat(retrievedReview.getId()).isPositive();
        assertThat(retrievedReview.getCar().getId()).isPositive();
    }

    @Test
    void testGetAll() {
        reviewRepository.save(Review.builder()
                .stars(5)
                .build());

        List<Review> result = reviewRepository.findAll();

        assertThat(result).isNotNull().
                hasSize(1);
    }
}
