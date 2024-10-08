package com.sunny.conoyabackend.repository;

import com.sunny.conoyabackend.dto.UserDTO;
import com.sunny.conoyabackend.entity.UserEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

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
        userDTO.setUserEmail("test1@eamil.com");
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
    @DisplayName("일반회원 회원 수정")
    public void updateUser(){
        Optional<UserEntity> result = userRepository.findByUserEmail("test@email.com");
        UserDTO userDTO = new UserDTO();
        userDTO.setUserPassword("5678");
        userDTO.setUserNickname("바보얌");

        result.orElseThrow();

    }
}
