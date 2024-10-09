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

    // 사용자 정보 업데이트 메서드 선언
    void updateUser(UserDTO userDTO);
}
