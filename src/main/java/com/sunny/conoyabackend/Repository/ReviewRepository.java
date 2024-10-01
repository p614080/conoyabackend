package com.sunny.conoyabackend.Repository;

import com.sunny.conoyabackend.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
