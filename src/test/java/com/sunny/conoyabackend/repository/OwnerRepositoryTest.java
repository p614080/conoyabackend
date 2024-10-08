package com.sunny.conoyabackend.repository;

import com.sunny.conoyabackend.dto.OwnerDTO;
import com.sunny.conoyabackend.entity.OwnerEntity;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OwnerRepositoryTest {

    @Autowired
    private OwnerRepository ownerRepository;

    // 일반회원 로그인 테스트
    @Test
    @DisplayName("점주회원 로그인")
    public void ownerLogin(){
        OwnerDTO ownerDTO = new OwnerDTO();
        ownerDTO.setOwnerEmail("test@email.com");
        ownerDTO.setOwnerPassword("1234");
        Optional<OwnerEntity> foundOwner = ownerRepository.findByOwnerEmail(ownerDTO.getOwnerEmail());
        boolean loginSuccess = foundOwner.isPresent() && foundOwner.get().getOwnerPassword().equals(ownerDTO.getOwnerPassword());
    }

    // 일반회원 회원가입 테스트
    @Test
    @DisplayName("점주회원 가입")
    public void ownerJoin() {

        OwnerDTO ownerDTO = new OwnerDTO();
        ownerDTO.setOwnerEmail("test4@eamil.com");
        ownerDTO.setOwnerPassword("1234");

        System.out.println("==============================================================");
        System.out.println(ownerDTO.getOwnerEmail());

        OwnerEntity ownerEntity = new OwnerEntity().builder()
                .ownerEmail(ownerDTO.getOwnerEmail())
                .ownerPassword(ownerDTO.getOwnerPassword())
                .build();

        ownerRepository.save(ownerEntity);
    }

    @Test
    @DisplayName("점주회원 정보 수정")
    @Transactional
    public void updateUser() {
        // db에서 가져온 값이 있으면 OwnerEntity 타입으로 가져온다.
        Optional<OwnerEntity> result = ownerRepository.findByOwnerEmail("test@eamil.com");
        OwnerEntity ownerEntity = result.orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        // DTO를 만들고 업데이트 할 값을 넣어준다.
        OwnerDTO ownerDTO = new OwnerDTO();
        ownerDTO.setOwnerPassword("5678");

        // Entity에 DTO 값을 넣어준다.
        ownerEntity.setOwnerEmail(ownerDTO.getOwnerEmail());
        ownerEntity.setOwnerPassword(ownerDTO.getOwnerPassword());
        // 리포지토리에서 엔티티 save를 한다.
        ownerRepository.save(ownerEntity);
    }


    @Test
    @DisplayName("점주회원 탈퇴")
    public void deleteUser(){
        Optional<OwnerEntity> result = ownerRepository.findByOwnerEmail("test@email.com");
        OwnerEntity  ownerEntity = result.orElseThrow();
        ownerRepository.delete(ownerEntity);
    }

}