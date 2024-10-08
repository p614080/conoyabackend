package com.sunny.conoyabackend.repository;

import com.sunny.conoyabackend.dto.UserDTO;
import com.sunny.conoyabackend.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserRepositoryTests {

    @Autowired
   private UserRepository userRepository;

    // 일반회원 로그인 테스트
    @Test
    @DisplayName("일반회원 로그인")
    public void userLogin(){
        UserDTO userDTO = new UserDTO();
        userDTO.setUserEmail("test@email.com");
        userDTO.setUserPassword("1234");
        Optional<UserEntity> foundUser = userRepository.findByUserEmail(userDTO.getUserEmail());
        boolean loginSuccess = foundUser.isPresent() && foundUser.get().getUserPassword().equals(userDTO.getUserPassword());
    }

    // 일반회원 회원가입 테스트
    @Test
    @DisplayName("일반회원 가입")
    public void userJoin() {

        UserDTO userDTO = new UserDTO();
        userDTO.setUserEmail("test4@eamil.com");
        userDTO.setUserPassword("1234");
        userDTO.setUserNickname("하이용");

        System.out.println("==============================================================");
        System.out.println(userDTO.getUserEmail());

        UserEntity userEntity = new UserEntity().builder()
                .userEmail(userDTO.getUserEmail())
                .userPassword(userDTO.getUserPassword())
                .userNickname(userDTO.getUserNickname())
                .build();

        userRepository.save(userEntity);
    }

    @Test
    @DisplayName("일반회원 정보 수정")
    @Transactional
    public void updateUser() {
        // db에서 가져온 값이 있으면 UserEntity 타입으로 가져온다.
        Optional<UserEntity> result = userRepository.findByUserEmail("test@eamil.com");
        UserEntity userEntity = result.orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        // DTO를 만들고 업데이트 할 값을 넣어준다.
        UserDTO userDTO = new UserDTO();
        userDTO.setUserPassword("5678");
        userDTO.setUserNickname("안녕");

        // Entity에 DTO 값을 넣어준다.
        userEntity.setUserNickname(userDTO.getUserNickname());
        userEntity.setUserPassword(userDTO.getUserPassword());
        // 리포지토리에서 엔티티 save를 한다.
        userRepository.save(userEntity);
    }


    @Test
    @DisplayName("일반회원 탈퇴")
    public void deleteUser(){
        Optional<UserEntity> result = userRepository.findByUserEmail("test@email.com");
        UserEntity  userEntity = result.orElseThrow();
        userRepository.delete(userEntity);
    }
}
