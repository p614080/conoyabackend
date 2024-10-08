package com.sunny.conoyabackend.service;

import com.sunny.conoyabackend.dto.EmailDTO;
import com.sunny.conoyabackend.dto.UserDTO;
import com.sunny.conoyabackend.entity.UserEntity;
import com.sunny.conoyabackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserDTO userDTO;


    @Override
    public boolean checkLoginEmailDuplicate(String userEmail) {
        return userRepository.existsByUserEmail(userEmail);
    }

    @Override
    public boolean checkNicknameDuplicate(String userNickname) {
        return userRepository.existsByUserNickname(userNickname);
    }

    /**
     * 회원가입
     *
     * @param userDto
     * @return String
     */
    @Override
    public String join(UserDTO userDto) {
        // 이메일 중복 확인
        if (checkLoginEmailDuplicate(userDto.getUserEmail())) {
            return "이메일 중복";
        }
        // 닉네임 중복 확인
        if (checkNicknameDuplicate(userDto.getUserNickname())) {
            return "닉네임 중복";
        }
        // UserEntity 생성 및 저장
        UserEntity userEntity = UserEntity.builder()
                .userEmail(userDto.getUserEmail())
                .userPassword(userDto.getUserPassword())
                .userNickname(userDto.getUserNickname())
                .build();

        userRepository.save(userEntity);
        return "가입이 완료되었습니다.";
    }

    /**
     * 로그인
     */
    public UserEntity login(UserDTO userDTO) {
        Optional<UserEntity> result = userRepository.findByUserEmail(userDTO.getUserEmail());
        // userEmail와 일치하는 User 없으면 null return
        if (result.isEmpty()) {
            return null;
        }
        UserEntity user = result.get();
        //찾아온 User의 password와 입력된 password가 다르면 null return
        if (!user.getUserPassword().equals(userDTO.getUserPassword())) {
            return null;
        }
        return user;
    }


    /**
     * 임시 비밀번호 생성
     *
     * @return
     */
    @Override
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

    @Override
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
