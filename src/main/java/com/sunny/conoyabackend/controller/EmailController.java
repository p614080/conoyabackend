package com.sunny.conoyabackend.controller;


import com.sunny.conoyabackend.dto.EmailDTO;
import com.sunny.conoyabackend.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/email")
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/send-email")
    public ResponseEntity sendEmail() {
        EmailDTO emailMessage = EmailDTO.builder()
                .to("수신아이디@naver.com")
                .subject("테스트메일")
                .message("비밀번호 재설정을 위한 이메일입니다.")
                .build();
        emailService.sendMail(emailMessage);

        return new ResponseEntity(HttpStatus.OK);
    }
}
