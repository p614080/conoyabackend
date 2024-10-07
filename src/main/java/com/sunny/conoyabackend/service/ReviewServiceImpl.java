package com.sunny.conoyabackend.service;

import com.sunny.conoyabackend.dto.ReviewDTO;

import com.sunny.conoyabackend.repository.ReviewRepository;
import com.sunny.conoyabackend.domain.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{
    private final ModelMapper modelMapper;
    private final ReviewRepository reviewRepository;

    @Override
    public Long register(ReviewDTO reviewDTO) {
        Review review = modelMapper.map(reviewDTO, Review.class);
        Review result = reviewRepository.save(review);
        return result.getReview_no();
    }

    @Override
    public ReviewDTO get(Long review_no){
        Optional<Review> result = reviewRepository.findById(review_no);
        Review review = result.orElseThrow();
        ReviewDTO reviewDTO = modelMapper.map(review, ReviewDTO.class);
        return reviewDTO;
    }

    @Override
    public void remove(Long review_no){
        reviewRepository.deleteById(review_no);
    }

    @Override
    public Page<ReviewDTO> getReviews(Pageable pageable) {
        Page<Review> reviews = reviewRepository.findAll(pageable);
        return reviews.map(review -> modelMapper.map(review, ReviewDTO.class));
    }

}
