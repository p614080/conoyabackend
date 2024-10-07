package com.sunny.conoyabackend.repository;

import com.sunny.conoyabackend.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findAll(Pageable pageable);

    @Query("SELECT r FROM Review r JOIN FETCH r.userEntity WHERE r.ownerEntity.ownerId = :ownerId")
    List<Review> findAllByOwnerEntity_OwnerId(@Param("ownerId") Long ownerId);
}
