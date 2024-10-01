package com.sunny.conoyabackend.Service;

import com.sunny.conoyabackend.DTO.ReviewDTO;
import com.sunny.conoyabackend.Repository.ReviewRepository;
import com.sunny.conoyabackend.domain.Review;
import lombok.RequiredArgsConstructor;
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
    public Long register(ReviewDTO reviewDTO){
        Review review =modelMapper.map(reviewDTO, Review.class);
        Review result =reviewRepository.save(review);
        return review.getRno();
    }

    @Override
    public ReviewDTO get(Long rno){
        Optional<Review> result = reviewRepository.findById(rno);
        Review review = result.orElseThrow();
        ReviewDTO reviewDTO = modelMapper.map(review, ReviewDTO.class);
        return reviewDTO;
    }

    @Override
    public void remove(Long rno){
        reviewRepository.deleteById(rno);
    }

}
