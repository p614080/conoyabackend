package com.sunny.conoyabackend.controller;



import com.sunny.conoyabackend.service.EmailService;
import com.sunny.conoyabackend.service.OwnerService;
import com.sunny.conoyabackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/email")
public class EmailController {

    private final EmailService emailService;
    private final OwnerService ownerService;
    private final UserService userService;

    // 사업자 회원 비밀번호 찾기
    @PostMapping("/send-owner-password")
    public ResponseEntity<String> sendOwnerTemporaryPassword(
            @RequestParam String ownerEmail // 사용자 이메일만 입력받음
    ) {
        try {
            // 사업자 회원에게 임시 비밀번호 발송
            ownerService.sendTemporaryPassword(ownerEmail);
            return ResponseEntity.ok("임시 비밀번호가 사업자 이메일로 전송되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("사업자 이메일로 비밀번호 발송 중 오류가 발생했습니다.");
        }
    }

    // 일반 회원 비밀번호 찾기
    @PostMapping("/send-user-password")
    public ResponseEntity<String> sendUserTemporaryPassword(
            @RequestParam String userEmail // 사용자 이메일만 입력받음
    ) {
        try {
            // 일반 회원에게 임시 비밀번호 발송
            userService.sendTemporaryPassword(userEmail);
            return ResponseEntity.ok("임시 비밀번호가 일반 회원 이메일로 전송되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("일반 회원 이메일로 비밀번호 발송 중 오류가 발생했습니다.");
        }
    }
}




