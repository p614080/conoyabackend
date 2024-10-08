package com.sunny.conoyabackend.service;

import com.sunny.conoyabackend.dto.EmailDTO;
import com.sunny.conoyabackend.dto.JoinDTO;
import com.sunny.conoyabackend.dto.LoginDTO;
import com.sunny.conoyabackend.dto.UserDTO;
import com.sunny.conoyabackend.entity.UserEntity;
import com.sunny.conoyabackend.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final EmailService emailService;
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

    // 로그아웃 기능
    public UserEntity logout(HttpServletRequest request, UserEntity user) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // 세션 무효화
        }
        return user; // 로그아웃된 사용자 정보를 반환할 수 있습니다.
    }

    // 닉네임 변경
    public UserEntity updateNickname(Long userId, UserDTO nicknameUpdate) {
        // userId로 유저 엔티티 조회
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // DTO에서 받은 새 닉네임으로 엔티티의 닉네임 변경
        user.setUserNickname(nicknameUpdate.getUserNickname());

        // 변경된 엔티티를 저장하고 반환
        return userRepository.save(user);
    }

    // 비밀번호 변경
    public UserEntity changePassword(Long userId, UserDTO passwordUserDTO) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));
        // 기존 비밀번호 확인
        if (!user.getUserPassword().equals(passwordUserDTO.getUserPassword())) {
            throw new RuntimeException("Incorrect old password");
        }
        // 새 비밀번호 설정
        user.setUserPassword(passwordUserDTO.getUserPassword());

        // 엔티티 저장 후 반환
        return userRepository.save(user);
    }

    //     // 회원 탈퇴
    public void deleteMember(Long userId) {
        userRepository.deleteById(userId);
    }


    // 임시 비밀번호 생성
    public String generateTemporaryPassword() {
        int length = 8;  // 임시 비밀번호 길이 설정
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            password.append(characters.charAt(random.nextInt(characters.length())));
        }
        return password.toString();
    }

    // 일반 회원 비밀번호 찾기
    public void sendTemporaryPassword(String userEmail) {
        // 사용자 조회
        UserEntity user = userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("해당 이메일을 가진 사용자가 없습니다."));

        // 임시 비밀번호 생성
        String temporaryPassword = generateTemporaryPassword();

        // 이메일로 임시 비밀번호 전송
        EmailDTO emailMessage = EmailDTO.builder()
                .to(userEmail)
                .subject("임시 비밀번호 발급 안내")
                .message("임시 비밀번호: " + temporaryPassword + "\n로그인 후 비밀번호를 변경하세요.")
                .build();
        emailService.sendMail(emailMessage);

        // 사용자 비밀번호 업데이트 (암호화 없이 그대로 저장)
        user.setUserPassword(temporaryPassword); // 암호화 없이 그대로 저장
        userRepository.save(user);
    }
}
