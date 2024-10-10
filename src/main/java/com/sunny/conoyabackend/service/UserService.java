package com.sunny.conoyabackend.service;

import com.sunny.conoyabackend.dto.UserDTO;
import com.sunny.conoyabackend.entity.UserEntity;
import com.sunny.conoyabackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface UserService {

    public boolean checkLoginEmailDuplicate(String userEmail);

    public boolean checkNicknameDuplicate(String userNickname);

    public String join(UserDTO userDto);

    public UserEntity login(UserDTO userDTO);

    // 임시 비밀번호 생성
    public String generateTemporaryPassword();

    void sendTemporaryPassword(String userEmail);

    // 사용자 정보를 이메일로 조회
    UserDTO getUserByEmail(String userEmail);

    // 닉네임 업데이트
    void updateNickname(String userEmail, String newNickname);

    // 비밀번호 업데이트
    void updatePassword(String userEmail, String newPassword);
    }
