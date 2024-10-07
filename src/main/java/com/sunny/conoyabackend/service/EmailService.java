package com.sunny.conoyabackend.service;

import com.sunny.conoyabackend.dto.EmailDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;

    // 발신자 이메일을 properties 파일에서 가져옴
  @Value("${spring.mail.username}") // spring.mail.username은 발신자 이메일 주소를 의미
    private String from;

    public void sendMail(EmailDTO emailMessage) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage(); // MimeMessage 객체 생성
        try {
            //MimeMessageHelper를 사용하여 보다 쉽게 MimeMessage를 구성할 수 있다.
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");

            // 이메일 수신자 설정
            mimeMessageHelper.setTo(emailMessage.getTo());

            // 이메일 제목 설정
            mimeMessageHelper.setSubject(emailMessage.getSubject());

            // 본문 내용 설정, false는 HTML 형식의 메세지를 사용하지 않음을 나타낸다.
            mimeMessageHelper.setText(emailMessage.getMessage(), false);

            // 이메일 발신자 설정
            mimeMessageHelper.setFrom(new InternetAddress(from)); // 발신자 이메일을 설정

            // 이메일 보내기
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }



}
