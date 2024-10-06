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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FavoritesRepositoryTests {
    private static final Logger log = LoggerFactory.getLogger(FavoritesRepositoryTests.class);
    @MockBean
    private JavaMailSender javaMailSender;

    @Autowired
    private FavoritesRepository favoritesRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Test
    @DisplayName("즐겨찾기 생성 테스트")
    public void test1_favorite_insert(){
        // 사용자 엔티티 생성 및 저장
        UserEntity userEntity = UserEntity.builder()
                .userEmail("abc@naver.com")
                .userNickname("우동 박완규")
                .userPassword("ewfr3&ddf5")
                .build();
        userEntity = userRepository.save(userEntity); // 저장

        // 가게 엔티티 생성 및 저장
        OwnerEntity ownerEntity = OwnerEntity.builder()
                .ownerEmail("nbnik@gmail.com")
                .ownerNum("234122356346")
                .ownerPassword("woi123331!")
                .storeName("행복 노래방")
                .location("부산광역시 해운대구")
                .description("전국 최대 규모 노래방")
                .build();
        ownerEntity = ownerRepository.save(ownerEntity); // 저장

        // 즐겨찾기 생성
        Favorites favorites = Favorites.builder()
                .userEntity(userEntity)
                .ownerEntity(ownerEntity)
                .build();
        favoritesRepository.save(favorites); // 즐겨찾기 저장

        // 즐겨찾기 저장 확인
        assertTrue(favoritesRepository.findByUserEntityAndOwnerEntity(userEntity, ownerEntity).isPresent());

        log.info("즐겨찾기 생성 및 확인 성공");
    }
}
