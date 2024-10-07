package com.sunny.conoyabackend.service;

import com.sunny.conoyabackend.dto.ReviewCommentDTO;

public interface ReviewCommentService {
    Long register(ReviewCommentDTO reviewCommentDTO);
    ReviewCommentDTO get(Long reviewNo);
    void remove(Long reviewNo);
}