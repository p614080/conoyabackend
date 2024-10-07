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

    @GetMapping("/{reviewNo}")
    public ReviewDTO get(@PathVariable(name="reviewNo") Long reviewNo){
        return service.get(reviewNo);
    }

    @GetMapping("/{ownerId/reviews")
    public Page<ReviewDTO> list(Pageable pageable) {
        return service.getReviews(pageable);
    }

    @PostMapping("/")
    public Map<String, Long> register(@RequestBody ReviewDTO reviewDTO){
        Long reviewNo = service.register(reviewDTO);
        return Map.of("reviewNo", reviewNo);
    }

    @DeleteMapping("/{reviewNo}")
    public Map<String, String> remove(@PathVariable(name = "reviewNo") Long reviewNo){
        service.remove(reviewNo);
        return Map.of("result", "SUCCESS");
    }
}
