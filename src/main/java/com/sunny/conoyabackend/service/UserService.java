package com.sunny.conoyabackend.service;

import com.sunny.conoyabackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    /*
     * LoginEmail 중복체크
     * 회원가입 기능 구현 시 사용
     * 중복되면 true return
     */
    public boolean checkLoginEmailDuplicate(String loginEmail) {
        return userRepository.existByLoginEmail(loginEmail);
    }

    /**
     *  nickname 중복체크
     *  회원가입 기능 구현 시 사용
     *  중복되면 true return
     */
    public boolean checkNicknameDuplicate(String nickname) {
        return userRepository.existByNickname(nickname);
    }

    /**
     * 회원가입 기능
     */
}
