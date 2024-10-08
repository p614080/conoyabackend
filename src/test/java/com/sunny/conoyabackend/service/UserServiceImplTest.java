package com.sunny.conoyabackend.service;

import com.sunny.conoyabackend.dto.UserDTO;
import com.sunny.conoyabackend.entity.UserEntity;
import com.sunny.conoyabackend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImplTest.class);
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        // MockitoAnnotations.openMocks(this); // Not needed with @ExtendWith
    }

    @Test
    @DisplayName("회원 가입 성공 테스트")
    void testJoin_Success() {
        UserDTO userDto = new UserDTO();
        userDto.setUserEmail("test5@example.com");
        userDto.setUserPassword("1234");
        userDto.setUserNickname("testUser");

        // Mocking the repository methods
        when(userRepository.existsByUserEmail(userDto.getUserEmail())).thenReturn(false);
        when(userRepository.existsByUserNickname(userDto.getUserNickname())).thenReturn(false);

        String message = userService.join(userDto);

        assertEquals("가입이 완료되었습니다.", message);
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    @DisplayName("임시 비밀번호 발급")
    void generateTemporaryPasswordTest(){
        // 임시 비밀번호 생성
        String tempPassword = userService.generateTemporaryPassword();

        // 검증: 비밀번호 길이가 8인지 확인
        assertEquals(8, tempPassword.length());

        // 검증: 비밀번호가 허용된 문자만 포함하고 있는지 확인
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        for (char c : tempPassword.toCharArray()) {
            assertTrue(characters.indexOf(c) >= 0, "비밀번호에 허용되지 않은 문자가 포함되어 있습니다: " + c);
        }
    }
    @Test
    @DisplayName("유저 로그인 성공")
    void testLogin_Success() {
        // 준비: 유저 정보를 담은 DTO 생성
        UserDTO userDto = new UserDTO();
        userDto.setUserEmail("test@example.com");
        userDto.setUserPassword("password123"); // 입력 비밀번호

        // 준비: 데이터베이스에서 찾을 UserEntity 생성
        UserEntity userEntity = UserEntity.builder() // 빌더 패턴 사용
                .userEmail("test@example.com")
                .userPassword("password123") // 비밀번호 일치
                .build();

        // Mocking: repository에서 이메일로 유저를 찾을 때, 사용자 정보를 반환하도록 설정
        when(userRepository.findByUserEmail(userDto.getUserEmail())).thenReturn(Optional.of(userEntity));

        // 실행: 로그인 메서드 호출
        UserEntity result = userService.login(userDto);

        // 검증: 로그인 결과가 기대한 사용자와 일치하는지 확인
        assertEquals(userEntity, result);
    }

    @Test
    void testLogin_UserNotFound() {
        // 준비: 유저 정보를 담은 DTO 생성
        UserDTO userDto = new UserDTO();
        userDto.setUserEmail("notfound@example.com");
        userDto.setUserPassword("password123");

        // Mocking: repository에서 이메일로 유저를 찾을 때, Optional.empty()를 반환하도록 설정
        when(userRepository.findByUserEmail(userDto.getUserEmail())).thenReturn(Optional.empty());

        // 실행: 로그인 메서드 호출
        UserEntity result = userService.login(userDto);

        // 검증: 결과가 null인지 확인
        assertEquals(null, result);
    }

    @Test
    void testLogin_IncorrectPassword() {
        // 준비: 유저 정보를 담은 DTO 생성
        UserDTO userDto = new UserDTO();
        userDto.setUserEmail("test@example.com");
        userDto.setUserPassword("wrongPassword");

        // 준비: 데이터베이스에서 찾을 UserEntity 생성
        UserEntity userEntity = UserEntity.builder() // 빌더 패턴 사용
                .userEmail("test@example.com")
                .userPassword("password123") // 비밀번호 일치
                .build();

        // Mocking: repository에서 이메일로 유저를 찾을 때, 사용자 정보를 반환하도록 설정
        when(userRepository.findByUserEmail(userDto.getUserEmail())).thenReturn(Optional.of(userEntity));

        // 실행: 로그인 메서드 호출
        UserEntity result = userService.login(userDto);

        // 검증: 결과가 null인지 확인
        assertEquals(null, result);
    }


}
