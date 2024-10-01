package com.sunny.conoyabackend.controller;

import com.sunny.conoyabackend.dto.JoinRequest;
import com.sunny.conoyabackend.dto.LoginRequest;
import com.sunny.conoyabackend.entity.User;
import com.sunny.conoyabackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;


    /**
     * 회원가입 API
     * @param joinRequest 회원가입 요청 데이터
     * @return HTTP 상태 코드
     */
    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody JoinRequest joinRequest) {
        userService.join(joinRequest);  // 회원가입 서비스 호출
        return new ResponseEntity<>("User successfully registered", HttpStatus.CREATED);  // 성공 메시지와 상태 코드 반환
    }
    // 이메일로 로그인
   @PostMapping("/login")
   public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest) {
       User loggedInMember = userService.login(loginRequest);
       return ResponseEntity.ok(loggedInMember); // 로그인 성공 시 회원 정보 반환
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
    public ResponseEntity<User> updateNickname(@PathVariable Long userId, @RequestParam String userNickname) {
        User updatedMember = userService.updateNickname(userId, userNickname);
        return ResponseEntity.ok(updatedMember);
    }

    // 비밀번호 변경
    @PutMapping("/{id}/password")
    public ResponseEntity<User> changePassword(@PathVariable Long userId, @RequestParam String oldPassword, @RequestParam String newPassword) {
        User updatedUser = userService.changePassword(userId, oldPassword, newPassword);
        return ResponseEntity.ok(updatedUser);
    }

    // 회원 탈퇴
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long userId) {
        userService.deleteMember(userId);
        return ResponseEntity.noContent().build();
    }
}
