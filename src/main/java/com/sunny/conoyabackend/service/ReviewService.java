package com.sunny.conoyabackend.service;

import com.sunny.conoyabackend.dto.ReviewDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewService {
    Long register(ReviewDTO reviewDTO);
    ReviewDTO get(Long review_no);
    void remove(Long review_no);
    Page<ReviewDTO> getReviews(Pageable pageable);
}
