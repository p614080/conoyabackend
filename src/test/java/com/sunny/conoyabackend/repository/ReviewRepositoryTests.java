package com.sunny.conoyabackend.repository;

import com.sunny.conoyabackend.domain.Review;
import com.sunny.conoyabackend.entity.OwnerEntity;
import com.sunny.conoyabackend.entity.UserEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class ReviewRepositoryTests {
    private static final Logger log = LoggerFactory.getLogger(ReviewRepositoryTests.class);
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private OwnerRepository ownerRepository; // OwnerRepository 추가

    @Autowired
    private UserRepository userRepository; // UserRepository 추가

    @Test
    @DisplayName("리파지토리 호출")
    public void test_call_repository(){
        log.info("====리파지토리 호출====");
        log.info(reviewRepository.toString());
    }

//    @Test
//    @DisplayName("리파지토리 Insert 테스트")
//    public void test_insert_repository() {
//        // OwnerEntity와 UserEntity를 먼저 저장합니다.
//        OwnerEntity owner = OwnerEntity.builder()
//                .ownerId(10000L)
//                .ownerEmail("kjwef@naver.com")
//                .build();
//        owner = ownerRepository.save(owner); // owner 저장
//
//        UserEntity user = UserEntity.builder()
//                .userId(10001L)
//                .userNickname("오야")
//                .userEmail("ljd@jwefnw.com")
//                .build();
//        user = userRepository.save(user); // user 저장
//
//        Review review = Review.builder()
//                .ownerEntity(owner) // 저장된 owner 사용
//                .userEntity(user) // 저장된 user 사용
//                .rating((short) 3)
//                .reviewContent("시설이 생각보다 어중간하네요")
//                .dueDate(LocalDate.of(2024, 10, 1))
//                .build();
//
//        Review result = reviewRepository.save(review);
//
//        log.info("====리파지토리 Insert====");
//        log.info(result.toString());
//    }

    @Test
    @DisplayName("리파지토리 Insert 테스트 - 다수의 사용자 및 다양한 리뷰 내용")
    public void test_insert_multiple_users_and_varied_reviews() {
        // 1개의 OwnerEntity를 저장
        OwnerEntity owner = OwnerEntity.builder()
                .ownerId(10000L)
                .ownerEmail("kjwef@naver.com")
                .build();
        owner = ownerRepository.save(owner); // owner 저장

        // 리뷰 내용을 평점에 따라 다르게 설정
        String[] reviewContents = {
                "정말 최악입니다. 다시는 오고 싶지 않아요.",   // 1점
                "별로였어요. 개선이 필요해요.",               // 2점
                "그럭저럭 괜찮았지만, 아쉬움이 남아요.",       // 3점
                "꽤 만족스러웠어요! 작은 문제들만 해결되면 좋겠네요.", // 4점
                "정말 훌륭했어요! 완벽한 경험이었습니다."      // 5점
        };

        // 10개의 UserEntity와 Review 데이터를 저장
        for (int i = 1; i <= 10; i++) {
            // UserEntity 생성 및 저장
            UserEntity user = UserEntity.builder()
                    .userId((long) (10000 + i)) // 고유한 userId
                    .userNickname("오야" + i)    // 고유한 닉네임
                    .userEmail("user" + i + "@example.com") // 고유한 이메일
                    .build();
            user = userRepository.save(user); // user 저장

            // 평점은 1부터 5까지 순환하도록 설정
            short rating = (short) (i % 5 + 1);

            // 평점에 따른 리뷰 내용 설정
            String reviewContent = reviewContents[rating - 1];

            // Review 생성 및 저장
            Review review = Review.builder()
                    .ownerEntity(owner) // 동일한 owner 사용
                    .userEntity(user)   // 저장된 user 사용
                    .rating(rating)     // 1부터 5까지의 평점
                    .reviewContent(reviewContent + " (" + i + "번 리뷰)") // 고유한 내용 추가
                    .dueDate(LocalDate.of(2024, 10, 1))
                    .build();

            Review result = reviewRepository.save(review);

            log.info("====리파지토리 Insert - Review {}====", i);
            log.info(result.toString());
        }
    }
    @Test
    @DisplayName("리파지토리 Insert 테스트 - 2번째 가게의 리뷰 입력 테스트")
    public void test_insert_multiple_users_and_varied_reviews2() {
        // 1개의 OwnerEntity를 저장
        OwnerEntity owner = OwnerEntity.builder()
                .ownerEmail("adbf@gmail.com")
                .build();
        owner = ownerRepository.save(owner); // owner 저장

        // 리뷰 내용을 평점에 따라 다르게 설정
        String[] reviewContents = {
                "정말 최악입니다. 다시는 오고 싶지 않아요.",   // 1점
                "별로였어요. 개선이 필요해요.",               // 2점
                "그럭저럭 괜찮았지만, 아쉬움이 남아요.",       // 3점
                "꽤 만족스러웠어요! 작은 문제들만 해결되면 좋겠네요.", // 4점
                "정말 훌륭했어요! 완벽한 경험이었습니다."      // 5점
        };

        // 10개의 UserEntity와 Review 데이터를 저장
        for (int i = 1; i <= 10; i++) {
            // UserEntity 생성 및 저장
            UserEntity user = UserEntity.builder()
                    .userId((long) (10000 + i)) // 고유한 userId
                    .userNickname("베이" + i)    // 고유한 닉네임
                    .userEmail("user2" + i + "@example.com") // 고유한 이메일
                    .build();
            user = userRepository.save(user); // user 저장

            // 평점은 1부터 5까지 순환하도록 설정
            short rating = (short) (i % 5 + 1);

            // 평점에 따른 리뷰 내용 설정
            String reviewContent = reviewContents[rating - 1];

            // Review 생성 및 저장
            Review review = Review.builder()
                    .ownerEntity(owner) // 동일한 owner 사용
                    .userEntity(user)   // 저장된 user 사용
                    .rating(rating)     // 1부터 5까지의 평점
                    .reviewContent(reviewContent + " (" + i + "번 리뷰) 2번째") // 고유한 내용 추가
                    .dueDate(LocalDate.of(2024, 10, 1))
                    .build();

            Review result = reviewRepository.save(review);

            log.info("====리파지토리 Insert - Review {}====", i);
            log.info(result.toString());
        }
    }

    @Test
    @DisplayName("ownerId 1번 점주의 모든 리뷰 조회 및 평균 평점 계산")
    public void test_get_all_reviews_and_calculate_average_rating_for_owner() {
        // OwnerId가 1번인 점주의 모든 리뷰 조회
        List<Review> reviews = reviewRepository.findAllByOwnerEntity_OwnerId(1L); // ownerId = 1

        // 리뷰가 존재하지 않으면 예외를 던지거나 로깅
        if (reviews.isEmpty()) {
            log.info("ownerId가 1번인 점주에게 등록된 리뷰가 없습니다.");
            return;
        }

        log.info("==== ownerId 1번 점주의 리뷰 목록 ====");

        // 총 평점 계산을 위한 변수
        double totalRating = 0;

        // 리뷰를 순회하면서 각 리뷰의 rating, reviewContent, dueDate를 출력
        for (Review review : reviews) {
            log.info("Rating: {}, ReviewContent: {}, DueDate: {}",
                    review.getRating(),
                    review.getReviewContent(),
                    review.getDueDate());

            // 총 평점 누적
            totalRating += review.getRating();
        }

        // 평균 평점 계산
        double averageRating = totalRating / reviews.size();

        // 평균 평점 출력
        log.info("==== ownerId 1번 점주의 평균 평점: {} ====", averageRating);
    }


}