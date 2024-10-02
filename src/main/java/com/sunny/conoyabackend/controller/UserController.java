package com.sunny.conoyabackend.controller;

import com.sunny.conoyabackend.dto.JoinDTO;
import com.sunny.conoyabackend.dto.LoginDTO;
import com.sunny.conoyabackend.dto.UserDTO;
import com.sunny.conoyabackend.entity.UserEntity;
import com.sunny.conoyabackend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    /**
     * 회원가입 API
     * @param joinDTO 회원가입 요청 데이터
     * @return HTTP 상태 코드
     */
    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody JoinDTO joinDTO, BindingResult bindingResult) {
        userService.join(joinDTO);  // 회원가입 서비스 호출
        // 비밀번호 일치 검증
        if (!joinDTO.getUserPassword().equals(joinDTO.getUserPasswordCheck())) {
            bindingResult.rejectValue("password", "passwordCheck",
                    "2개의 패스워드가 일치하지 않습니다.");
        }
        // 이미 등록된 사용자
        try {
            userService.join(joinDTO);
        }catch(DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");

        }catch(Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());

        }

        return new ResponseEntity<>("User successfully registered", HttpStatus.CREATED);  // 성공 메시지와 상태 코드 반환
    }
    // 로그인
    @PostMapping("/login")
    public ResponseEntity<UserEntity> login(@RequestBody LoginDTO loginDTO) {
        UserEntity loggedInMember = userService.login(loginDTO);
        return ResponseEntity.ok(loggedInMember); // 로그인 성공 시 회원 정보 반환
    }
    // 로그아웃
    @PostMapping("/logout")
    public UserEntity logout(HttpServletRequest request, @RequestBody UserEntity user) {
        return userService.logout(request, user);
    }
    // 이메일 중복 체크 API
    @GetMapping("/check-email")
    public ResponseEntity<Boolean> checkEmail(@RequestParam String email) {
        boolean isDuplicate = userService.checkLoginEmailDuplicate(email);
        return ResponseEntity.ok(isDuplicate);
    }

    // 닉네임 중복 체크 API
    @GetMapping("/check-nickname")
    public ResponseEntity<Boolean> checkNickname(@RequestParam String nickname) {
        boolean isDuplicate = userService.checkNicknameDuplicate(nickname);
        return ResponseEntity.ok(isDuplicate);
    }

    // 닉네임 변경
    @PutMapping("/{id}/nickname")
    public ResponseEntity<UserEntity> updateNickname(@PathVariable Long userId, @RequestParam UserDTO nicknameUpdate ) {
        UserEntity updatedMember = userService.updateNickname(userId, nicknameUpdate);
        return ResponseEntity.ok(updatedMember);
    }

    // 비밀번호 변경
    @PutMapping("/{id}/password")
    public ResponseEntity<UserEntity> changePassword(@PathVariable Long userId, @RequestParam UserDTO passwordUserDTO) {
        UserEntity updatedUserEntity = userService.changePassword(userId, passwordUserDTO);
        return ResponseEntity.ok(updatedUserEntity);
    }

    // 회원 탈퇴
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long userId) {
        userService.deleteMember(userId);
        return ResponseEntity.noContent().build();
    }
}
