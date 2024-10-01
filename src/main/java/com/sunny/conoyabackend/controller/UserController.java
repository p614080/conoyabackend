package com.sunny.conoyabackend.controller;

import com.sunny.conoyabackend.entity.User;
import com.sunny.conoyabackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    // 이메일로 로그인
//   @PostMapping("/login")
//   public ResponseEntity<User> login(@RequestParam String userEmail, @RequestParam String userPassword) {
//       User loggedInMember = userService.login(userEmail, userPassword);
//       return ResponseEntity.ok(loggedInMember); // 로그인 성공 시 회원 정보 반환
//    }

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
