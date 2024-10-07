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
public class ReviewCommentRepositoryTests {

    private static final Logger log = LoggerFactory.getLogger(ReviewRepositoryTests.class);

    @Autowired
    private ReviewRepository reviewRepository; // ReviewRepository 주입

    @Autowired
    private ReviewCommentRepository reviewCommentRepository; // ReviewCommentRepository 주입

    @Test
    @DisplayName("ownerId 1번의 모든 리뷰 조회 후 첫번째 리뷰의 댓글 처리")
    public void test_review_comments_for_first_review() {
        // ownerId가 1번인 점주의 모든 리뷰 조회
        List<Review> reviews = reviewRepository.findAllByOwnerEntity_OwnerId(1L);

        // 리뷰가 존재하지 않으면 예외를 던지거나 로깅
        if (reviews.isEmpty()) {
            log.info("ownerId가 1번인 점주에게 등록된 리뷰가 없습니다.");
            return;
        }

        log.info("==== ownerId 1번 점주의 리뷰 목록 ====");

        // 모든 리뷰를 순회하며 출력
        reviews.forEach(review -> {
            log.info("Rating: {}, ReviewContent: {}, Nickname: {}, DueDate: {}",
                    review.getRating(),
                    review.getReviewContent(),
                    review.getUserEntity().getUserNickname(),
                    review.getDueDate());
        });

        // 첫 번째 리뷰 가져오기
        Review firstReview = reviews.get(0);

        // 첫 번째 리뷰에 연결된 댓글이 있는지 확인
        ReviewComment reviewComment = reviewCommentRepository.findByReview_ReviewNo(firstReview.getReviewNo());

        if (reviewComment != null) {
            // 댓글이 있으면 삭제
            reviewCommentRepository.deleteById(1L);
            reviewCommentRepository.flush();
            log.info("첫 번째 리뷰의 댓글이 삭제되었습니다.");
        } else {
            // 댓글이 없으면 새로운 댓글 등록
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
