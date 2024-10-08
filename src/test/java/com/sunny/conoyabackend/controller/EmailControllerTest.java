package com.sunny.conoyabackend.controller;

import com.sunny.conoyabackend.dto.EmailDTO;
import com.sunny.conoyabackend.service.EmailService;
import com.sunny.conoyabackend.service.OwnerService;
import com.sunny.conoyabackend.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmailController.class)
public class EmailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmailService emailService;

    @MockBean
    private OwnerService ownerService;

    @MockBean
    private UserService userService;

    @Test
    public void sendOwnerTemporaryPasswordTest() throws Exception {
        String ownerEmail = "owner@example.com";
        String senderEmail = "sender@example.com";

        // Mock OwnerService의 sendTemporaryPassword 메서드
        Mockito.doNothing().when(ownerService).sendTemporaryPassword(ownerEmail);

        // Mock EmailService의 setFrom 메서드
        Mockito.doNothing().when(emailService).setFrom(senderEmail);

        // Mock EmailService의 sendMail 메서드
        EmailDTO emailDTO = EmailDTO.builder()
                .to(ownerEmail)
                .subject("임시 비밀번호 발송")
                .message("임시 비밀번호가 이메일로 발송되었습니다.")
                .build();

        Mockito.doNothing().when(emailService).sendMail(emailDTO);

        // MockMvc를 사용하여 POST 요청 테스트
        mockMvc.perform(post("/api/v1/email/send-owner-password")
                        .param("ownerEmail", ownerEmail)
                        .param("senderEmail", senderEmail)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("임시 비밀번호가 사업자 이메일로 전송되었습니다."));
    }

    @Test
    public void sendUserTemporaryPasswordTest() throws Exception {
        String userEmail = "user@example.com";
        String senderEmail = "sender@example.com";

        // Mock UserService의 sendTemporaryPassword 메서드
        Mockito.doNothing().when(userService).sendTemporaryPassword(userEmail);

        // Mock EmailService의 setFrom 메서드
        Mockito.doNothing().when(emailService).setFrom(senderEmail);

        // Mock EmailService의 sendMail 메서드
        EmailDTO emailDTO = EmailDTO.builder()
                .to(userEmail)
                .subject("임시 비밀번호 발송")
                .message("임시 비밀번호가 이메일로 발송되었습니다.")
                .build();

        Mockito.doNothing().when(emailService).sendMail(emailDTO);

        // MockMvc를 사용하여 POST 요청 테스트
        mockMvc.perform(post("/api/v1/email/send-user-password")
                        .param("userEmail", userEmail)
                        .param("senderEmail", senderEmail)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("임시 비밀번호가 일반 회원 이메일로 전송되었습니다."));
    }
}
