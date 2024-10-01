package com.sunny.conoyabackend.service;

import com.sunny.conoyabackend.dto.ReviewDTO;

public interface ReviewService {
    Long register(ReviewDTO reviewDTO);
    ReviewDTO get(Long rno);
    void remove(Long rno);
}
