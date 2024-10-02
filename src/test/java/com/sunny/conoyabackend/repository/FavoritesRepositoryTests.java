package com.sunny.conoyabackend.repository;

import com.sunny.conoyabackend.domain.Favorites;
import com.sunny.conoyabackend.entity.OwnerEntity;
import com.sunny.conoyabackend.entity.UserEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FavoritesRepositoryTests {
    private static final Logger log = LoggerFactory.getLogger(FavoritesRepositoryTests.class);
    @Autowired
    private FavoritesRepository favoritesRepository;

    @Test
    @DisplayName("즐겨찾기 생성 테스트")
    public void test1_favorite_insert(){
        UserEntity userEntity = UserEntity.builder()
                .userEmail("abc@naver.com")
                .userNickname("우동 박완규")
                .userPassword("ewfr3&ddf5")
                .build();
        OwnerEntity ownerEntity = OwnerEntity.builder()
                .ownerEmail("nbnik@gmail.com")
                .ownerNum("234122356346")
                .ownerPassword("woi123331!")
                .storeName("행복 노래방")
                .location("부산광역시 해운대구")
                .description("전국 최대 규모 노래방")
                .build();
    }
}