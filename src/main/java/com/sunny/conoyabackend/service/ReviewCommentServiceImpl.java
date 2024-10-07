package com.sunny.conoyabackend.service;

import com.sunny.conoyabackend.domain.ReviewComment;
import com.sunny.conoyabackend.dto.ReviewCommentDTO;
import com.sunny.conoyabackend.repository.ReviewCommentRepository;
import com.sunny.conoyabackend.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewCommentServiceImpl implements ReviewCommentService {
    private final ModelMapper modelMapper;
    private final ReviewCommentRepository reviewCommentRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public Long register(ReviewCommentDTO reviewCommentDTO) {
        ReviewComment reviewComment = modelMapper.map(reviewCommentDTO, ReviewComment.class);
        reviewComment.setReview(reviewRepository.findById(reviewCommentDTO.getReviewNo()).orElseThrow());
        ReviewComment result = reviewCommentRepository.save(reviewComment);
        return result.getCommentNo();
    }

    @Override
    public ReviewCommentDTO get(Long reviewNo) {
        ReviewComment reviewComment = reviewCommentRepository.findByReview_ReviewNo(reviewNo);
        return modelMapper.map(reviewComment, ReviewCommentDTO.class);
    }

    @Override
    public void remove(Long commentNo) {
        reviewCommentRepository.deleteById(commentNo);
    }
}

