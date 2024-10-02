package com.sunny.conoyabackend.controller;


import com.sunny.conoyabackend.entity.OwnerEntity;
import com.sunny.conoyabackend.repository.OwnerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;


@SpringBootTest
class OwnerControllerTests {
    private static final Logger log = LoggerFactory.getLogger(OwnerControllerTests.class);
    @Autowired
    private OwnerRepository ownerRepository;

    @Test
    @DisplayName("리포지토리 호출")
    public void test_call_repository() {
        log.info("====리파지토리 호출====");
        log.info(ownerRepository.toString());
    }

    @Test
    @DisplayName("회원가입 테스트")
    public void test_insert_repository(){
        OwnerEntity ownerEntity = OwnerEntity.builder()
                .ownerId(100L)
                .ownerPassword("7788")
                .build();
        OwnerEntity result = ownerRepository.save(ownerEntity);
        log.info("====리파지토리 Insert====");
        log.info(result.toString());
    }

    @Test
    @DisplayName("리포지토리 Insert 100개 테스트")
    public void test_insert_repository_100() {
        for(int i = 1; i<=100; i++) {
            OwnerEntity owner = OwnerEntity.builder()
                    .ownerId(5L)
                    .ownerNum("nanna")
                    .ownerPassword("!@#!@$%")
                    .build();
            OwnerEntity result = ownerRepository.save(owner);
            log.info("===리파지토리 Insert x100===");
            log.info(result.toString());
        }
    }
    @Test
    @DisplayName("리파지토리 조회 테스트")
    public void test_find_repository(){
        Long userId =98L;
        Optional<OwnerEntity> result = ownerRepository.findById(userId);
        OwnerEntity owner = result.orElseThrow();
        log.info("====리파지토리 조회====");
        log.info(owner.toString());
    }
}