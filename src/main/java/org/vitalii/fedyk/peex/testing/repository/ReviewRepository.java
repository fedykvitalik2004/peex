package org.vitalii.fedyk.peex.testing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vitalii.fedyk.peex.testing.Car;
import org.vitalii.fedyk.peex.testing.Review;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
