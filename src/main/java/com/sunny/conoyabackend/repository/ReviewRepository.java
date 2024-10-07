package com.sunny.conoyabackend.repository;

import com.sunny.conoyabackend.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findAll(Pageable pageable);

    List<Review> findAllByOwnerEntity_OwnerId(long l);
}
