package com.sunny.conoyabackend.service;

import com.sunny.conoyabackend.dto.EmailDTO;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringJUnitConfig
public class EmailServiceTest {

    @Mock
    private JavaMailSender javaMailSender;

    @InjectMocks
    private EmailService emailService;

    @Test
    public void sendMailTest() throws Exception {
        // Mock MimeMessage
        MimeMessage mimeMessage = Mockito.mock(MimeMessage.class);
        Mockito.when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);


        // EmailDTO 객체 생성 (수신자 이메일 설정)
        EmailDTO emailDTO = EmailDTO.builder()
                .to("recipient@example.com")  // 수신자 이메일 설정
                .subject("Test Subject")
                .message("Test Message")
                .build();

        // 이메일 발송
        emailService.sendMail(emailDTO);

        // JavaMailSender의 send 메서드가 호출되었는지 확인
        verify(javaMailSender, times(1)).send(any(MimeMessage.class));
    }
}
