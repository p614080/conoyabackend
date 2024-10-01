package com.sunny.conoyabackend.service;

import com.sunny.conoyabackend.dto.JoinRequest;
import com.sunny.conoyabackend.dto.LoginRequest;
import com.sunny.conoyabackend.entity.User;
import com.sunny.conoyabackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
    public boolean checkLoginEmailDuplicate(String userEmail) {
        return userRepository.existsByUserEmail(userEmail);
    }

    /**
     * nickname 중복체크
     * 회원가입 기능 구현 시 사용
     * 중복되면 true return
     */
    public boolean checkNicknameDuplicate(String userNickname) {
        return userRepository.existsByUserNickname(userNickname);
    }

    /**
     * 회원가입 기능
     */
    public void join(JoinRequest userReq) {
        userRepository.save(userReq.userEntity());
    }

    /**
     * 로그인 기능
     */
    public User login(LoginRequest userReq) {
        Optional<User> optionalUser = userRepository.findByUserEmail(userReq.getUserEmail());

        // userEmail와 일치하는 User 없으면 null return
        if (optionalUser.isEmpty()) {
            return null;
        }

        User user = optionalUser.get();

        //찾아온 User의 password와 입력된 password가 다르면 null return
        if (!user.getUserPassword().equals(userReq.getUserPassword())) {
            return null;
        }
        return user;
    }
    // 닉네임 변경
    public User updateNickname(Long userId, String newNickname) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));
        user.setUserNickname(newNickname);
        return userRepository.save(user);
    }

    // 비밀번호 변경
    public User changePassword(Long userId, String oldPassword, String newPassword) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Member not found"));
        if (!user.getUserPassword().equals(oldPassword)) {
            throw new RuntimeException("Incorrect old password");
        }
        user.setUserPassword(newPassword);
        return userRepository.save(user);
    }

    //     // 회원 탈퇴
    public void deleteMember(Long userId) {
        userRepository.deleteById(userId);
    }
}
