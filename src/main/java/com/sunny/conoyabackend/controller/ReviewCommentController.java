package com.sunny.conoyabackend.controller;

import com.sunny.conoyabackend.dto.ReviewCommentDTO;
import com.sunny.conoyabackend.service.ReviewCommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/review-comments")
public class ReviewCommentController {
    private final ReviewCommentService reviewCommentService;

    // 리뷰 댓글 등록
    @PostMapping("/")
    public Map<String, Long> register(@RequestBody ReviewCommentDTO reviewCommentDTO) {
        Long commentNo = reviewCommentService.register(reviewCommentDTO);
        return Map.of("commentNo", commentNo);
    }

    // 특정 리뷰에 대한 댓글 조회
    @GetMapping("/{reviewNo}")
    public ReviewCommentDTO get(@PathVariable Long reviewNo) {
        return reviewCommentService.get(reviewNo);
    }

    // 리뷰 댓글 삭제
    @DeleteMapping("/{commentNo}")
    public Map<String, String> remove(@PathVariable Long commentNo) {
        reviewCommentService.remove(commentNo);
        return Map.of("result", "SUCCESS");
    }
}
