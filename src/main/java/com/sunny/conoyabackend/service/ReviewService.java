package com.sunny.conoyabackend.service;

import com.sunny.conoyabackend.dto.ReviewDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewService {
    Long register(ReviewDTO reviewDTO);
    ReviewDTO get(Long reviewNo);
    void remove(Long reviewNo);
    Page<ReviewDTO> getReviews(Pageable pageable);
}
