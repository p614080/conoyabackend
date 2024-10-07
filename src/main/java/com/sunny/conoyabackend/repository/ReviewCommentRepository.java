package com.sunny.conoyabackend.repository;

import com.sunny.conoyabackend.domain.ReviewComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewCommentRepository extends JpaRepository<ReviewComment, Long> {

    ReviewComment findByReview_ReviewNo(Long reviewNo);
}