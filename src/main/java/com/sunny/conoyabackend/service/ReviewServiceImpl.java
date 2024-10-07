package com.sunny.conoyabackend.service;

import com.sunny.conoyabackend.dto.PageRequestDTO;
import com.sunny.conoyabackend.dto.PageResponseDTO;
import com.sunny.conoyabackend.dto.ReviewDTO;

import com.sunny.conoyabackend.repository.ReviewRepository;
import com.sunny.conoyabackend.domain.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        return result.getReviewNo();
    }

    @Override
    public ReviewDTO get(Long reviewNo){
        Optional<Review> result = reviewRepository.findById(reviewNo);
        Review review = result.orElseThrow();
        ReviewDTO reviewDTO = modelMapper.map(review, ReviewDTO.class);
        return reviewDTO;
    }

    @Override
    public void remove(Long reviewNo){
        reviewRepository.deleteById(reviewNo);
    }

    @Override
    public PageResponseDTO<ReviewDTO> list(PageRequestDTO pageRequestDTO, Long ownerId){
        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage() - 1,
                pageRequestDTO.getSize(),
                Sort.by("reviewNo").descending()
        );
        Page<Review> result = reviewRepository.findAllByOwnerEntity_OwnerId(ownerId, pageable);
        List<ReviewDTO> dtoList = result.getContent().stream().map((todo)->modelMapper.map(todo, ReviewDTO.class))
                .collect(Collectors.toList());
        List<Review> tmps = result.getContent();

        Review.builder().build();
        long totalCount = result.getTotalElements();
        PageResponseDTO pageResponseDTO = PageResponseDTO.<ReviewDTO>withAll()
                .dtoList(dtoList)
                .pageRequestDTO(pageRequestDTO)
                .totalCount(totalCount)
                .build();
        return pageResponseDTO;
    }

}
