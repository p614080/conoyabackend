package com.sunny.conoyabackend.controller;

import com.sunny.conoyabackend.service.EmailService;
import com.sunny.conoyabackend.service.OwnerService;
import com.sunny.conoyabackend.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
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

    @Mock
    private JavaMailSender javaMailSender;

    @Test
    public void sendOwnerTemporaryPasswordTest() throws Exception {
        String ownerEmail = "owner@example.com";

        // Mock OwnerService의 sendTemporaryPassword 메서드
        Mockito.doNothing().when(ownerService).sendTemporaryPassword(ownerEmail);

        // 실제 POST 요청을 시뮬레이션하여 테스트
        mockMvc.perform(post("/api/v1/email/send-owner-password")
                        .param("ownerEmail", ownerEmail)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("임시 비밀번호가 사업자 이메일로 전송되었습니다."));
    }

    @Test
    public void sendUserTemporaryPasswordTest() throws Exception {
        String userEmail = "user@example.com";

        // Mock UserService의 sendTemporaryPassword 메서드
        Mockito.doNothing().when(userService).sendTemporaryPassword(userEmail);

        // 실제 POST 요청을 시뮬레이션하여 테스트
        mockMvc.perform(post("/api/v1/email/send-user-password")
                        .param("userEmail", userEmail)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("임시 비밀번호가 일반 회원 이메일로 전송되었습니다."));
    }
}
