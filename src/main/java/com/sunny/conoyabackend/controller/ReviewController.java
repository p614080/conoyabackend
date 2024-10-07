package com.sunny.conoyabackend.controller;

import com.sunny.conoyabackend.dto.ReviewDTO;
import com.sunny.conoyabackend.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/review")
public class ReviewController {
    private final ReviewService service;

    @GetMapping("/{review_no}")
    public ReviewDTO get(@PathVariable(name="review_no") Long review_no){
        return service.get(review_no);
    }

    @GetMapping("/{ownerId}/reviews")
    public Page<ReviewDTO> list(Pageable pageable) {
        return service.getReviews(pageable);
    }

    @PostMapping("/")
    public Map<String, Long> register(@RequestBody ReviewDTO reviewDTO){
        Long review_no = service.register(reviewDTO);
        return Map.of("review_no", review_no);
    }

    @DeleteMapping("/{review_no}")
    public Map<String, String> remove(@PathVariable(name = "review_no") Long review_no){
        service.remove(review_no);
        return Map.of("result", "SUCCESS");
    }
}
