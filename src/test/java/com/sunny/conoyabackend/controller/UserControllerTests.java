package com.sunny.conoyabackend.controller;


import com.sunny.conoyabackend.entity.UserEntity;
import com.sunny.conoyabackend.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;


@SpringBootTest
class UserControllerTests {
    private static final Logger log = LoggerFactory.getLogger(UserControllerTests.class);
    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("리포지토리 호출")
    public void test_call_repository() {
        log.info("====리파지토리 호출====");
        log.info(userRepository.toString());
    }

    @Test
    @DisplayName("회원가입 테스트")
    public void test_insert_repository(){
        UserEntity userEntity = UserEntity.builder()
                .userId(100L)
                .userEmail("ossetian@naver.com")
                .userPassword("7788")
                .userNickname("오오오")
                .build();
        UserEntity result = userRepository.save(userEntity);
        log.info("====리파지토리 Insert====");
        log.info(result.toString());
    }

    @Test
    @DisplayName("리포지토리 Insert 100개 테스트")
    public void test_insert_repository_100() {
        for(int i = 1; i<=100; i++) {
            UserEntity user = UserEntity.builder()
                    .userNickname("name" + i)
                    .userEmail("ossetian" + i + "@naver.com")
                    .userPassword("!@#!@$%")
                    .build();
            UserEntity result = userRepository.save(user);
            log.info("===리파지토리 Insert x100===");
            log.info(result.toString());
        }
    }
    @Test
    @DisplayName("리파지토리 조회 테스트")
    public void test_find_repository(){
        Long userId =98L;
        Optional<UserEntity> result = userRepository.findById(userId);
        UserEntity user = result.orElseThrow();
        log.info("====리파지토리 조회====");
        log.info(user.toString());
    }
}