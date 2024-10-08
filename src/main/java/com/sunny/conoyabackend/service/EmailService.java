package com.sunny.conoyabackend.service;

import com.sunny.conoyabackend.dto.EmailDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}") // 발신자 이메일을 프로퍼티 파일에서 자동 주입
    private String from; // 발신자 이메일을 자동으로 설정

    public void sendMail(EmailDTO emailMessage) {
        if (from == null || emailMessage.getTo() == null) {
            throw new IllegalArgumentException("발신자 또는 수신자 이메일이 설정되지 않았습니다.");
        }

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            mimeMessageHelper.setTo(emailMessage.getTo());
            mimeMessageHelper.setSubject(emailMessage.getSubject());
            mimeMessageHelper.setText(emailMessage.getMessage(), false);
            mimeMessageHelper.setFrom(new InternetAddress(from)); // 프로퍼티에서 설정된 발신자 이메일
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}





