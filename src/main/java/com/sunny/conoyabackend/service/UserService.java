package com.sunny.conoyabackend.service;

import com.sunny.conoyabackend.dto.JoinDTO;
import com.sunny.conoyabackend.dto.LoginDTO;
import com.sunny.conoyabackend.dto.UpdateDTO;
import com.sunny.conoyabackend.entity.UserEntity;
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
    public void join(JoinDTO userJoin) {
        userRepository.save(userJoin.userEntity());
    }

    /**
     * 로그인 기능
     */
    public UserEntity login(LoginDTO userReq) {
        Optional<UserEntity> optionalUser = userRepository.findByUserEmail(userReq.getUserEmail());

        // userEmail와 일치하는 User 없으면 null return
        if (optionalUser.isEmpty()) {
            return null;
        }

        UserEntity user = optionalUser.get();

        //찾아온 User의 password와 입력된 password가 다르면 null return
        if (!user.getUserPassword().equals(userReq.getUserPassword())) {
            return null;
        }
        return user;
    }
    // 닉네임 변경
    public UserEntity updateNickname(Long userId, UpdateDTO nicknameUpdate) {
        // userId로 유저 엔티티 조회
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // DTO에서 받은 새 닉네임으로 엔티티의 닉네임 변경
        user.setUserNickname(nicknameUpdate.getNewNickname());

        // 변경된 엔티티를 저장하고 반환
        return userRepository.save(user);
    }

    // 비밀번호 변경
    public UserEntity changePassword(Long userId, UpdateDTO passwordUpdateDTO) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));
        // 기존 비밀번호 확인
        if (!user.getUserPassword().equals(passwordUpdateDTO.getOldPassword())) {
            throw new RuntimeException("Incorrect old password");
        }
        // 새 비밀번호 설정
        user.setUserPassword(passwordUpdateDTO.getNewPassword());

        // 엔티티 저장 후 반환
        return userRepository.save(user);
    }

    //     // 회원 탈퇴
    public void deleteMember(Long userId) {
        userRepository.deleteById(userId);
    }
}
