package com.sunny.conoyabackend.Service;

import com.sunny.conoyabackend.DTO.ReviewDTO;

public interface ReviewService {
    Long register(ReviewDTO reviewDTO);
    ReviewDTO get(Long rno);
    void remove(Long rno);
}
