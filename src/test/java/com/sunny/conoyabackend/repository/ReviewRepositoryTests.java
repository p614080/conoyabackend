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

    @Test
    @DisplayName("리파지토리 Insert 테스트")
    public void test_insert_repository() {
        // OwnerEntity와 UserEntity를 먼저 저장합니다.
        OwnerEntity owner = OwnerEntity.builder()
                .ownerId(10000L)
                .build();
        owner = ownerRepository.save(owner); // owner 저장

        UserEntity user = UserEntity.builder()
                .userId(10001L)
                .userNickname("오야")
                .userEmail("ljd@jwefnw.com")
                .build();
        user = userRepository.save(user); // user 저장

        Review review = Review.builder()
                .ownerEntity(owner) // 저장된 owner 사용
                .userEntity(user) // 저장된 user 사용
                .rating((short) 3)
                .reviewContent("시설이 생각보다 어중간하네요")
                .dueDate(LocalDate.of(2024, 10, 1))
                .build();

        Review result = reviewRepository.save(review);

        log.info("====리파지토리 Insert====");
        log.info(result.toString());
    }

    @Test
    @DisplayName("리포지토리 Insert 100개 테스트")
    public void test_insert_repository_100() {
        for(int i = 1; i<=100; i++) {
            Review review = Review.builder()
                    .ownerEntity(OwnerEntity.builder().ownerId(10000L).build())
                    .userEntity(UserEntity.builder().userNickname("오야").build())
                    .rating((short) ((short) i%5))
                    .dueDate(LocalDate.of(2024, 9, 29))
                    .build();
            Review result = reviewRepository.save(review);
            log.info("===리파지토리 Insert x100===");
            log.info(result.toString());
        }
    }
    @Test
    @DisplayName("리파지토리 조회 테스트")
    public void test_find_repository(){
        Long review_no =50L;
        Optional<Review> result = reviewRepository.findById(review_no);
        Review review = result.orElseThrow();
        log.info("====리파지토리 조회====");
        log.info(review.toString());
    }


}