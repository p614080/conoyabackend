package com.sunny.conoyabackend.repository;

import com.sunny.conoyabackend.domain.Review;
import com.sunny.conoyabackend.domain.ReviewComment;
import com.sunny.conoyabackend.dto.ReviewDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ReviewCommentRepositoryTests {
    private static final Logger log = LoggerFactory.getLogger(FavoritesRepositoryTests.class);

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ReviewCommentRepository reviewCommentRepository;

    // Review를 DTO로 변환하는 메서드
    public ReviewDTO convertToDTO(Review review) {
        return ReviewDTO.builder()
                .reviewNo(review.getReviewNo())
                .ownerId(review.getOwnerEntity().getOwnerId())
                .userId(review.getUserEntity().getUserId())
                .rating(review.getRating())
                .reviewContent(review.getReviewContent())
                .dueDate(review.getDueDate())
                .build();
    }

    @Test
    @DisplayName("ownerId 1번의 모든 리뷰 조회 후 첫번째 리뷰의 댓글 처리")
    public void test_review_comments_for_first_review() {
        // ownerId가 1번인 점주의 모든 리뷰 조회
        List<Review> reviews = reviewRepository.findAllByOwnerEntity_OwnerId(1L);

        if (reviews.isEmpty()) {
            log.info("ownerId가 1번인 점주에게 등록된 리뷰가 없습니다.");
            return;
        }

        log.info("==== ownerId 1번 점주의 리뷰 목록 ====");

        // 리뷰를 DTO로 변환하여 출력
        reviews.forEach(review -> {
            ReviewDTO reviewDTO = convertToDTO(review);
            log.info("Rating: {}, ReviewContent: {}, UserId: {}, DueDate: {}",
                    reviewDTO.getRating(),
                    reviewDTO.getReviewContent(),
                    reviewDTO.getUserId(),
                    reviewDTO.getDueDate());
        });

        // 첫 번째 리뷰 가져오기
        Review firstReview = reviews.get(0);

        ReviewComment reviewComment = reviewCommentRepository.findByReview_ReviewNo(firstReview.getReviewNo());

        if (reviewComment != null) {
            reviewCommentRepository.delete(reviewComment);
            log.info("첫 번째 리뷰의 댓글이 삭제되었습니다.");
        } else {
            ReviewComment newComment = ReviewComment.builder()
                    .comment("감사합니다 더욱 노력하는 " + firstReview.getOwnerEntity().getStoreName() + " 노래방이 되겠습니다")
                    .review(firstReview)
                    .ownerEntity(firstReview.getOwnerEntity())
                    .commentDate(LocalDateTime.now())
                    .build();

            reviewCommentRepository.save(newComment);
            log.info("첫 번째 리뷰에 새로운 댓글이 등록되었습니다.");
        }
    }
}