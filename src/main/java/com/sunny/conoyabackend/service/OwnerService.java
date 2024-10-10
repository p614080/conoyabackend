package com.sunny.conoyabackend.service;

import com.sunny.conoyabackend.dto.*;
import com.sunny.conoyabackend.entity.OwnerEntity;
import com.sunny.conoyabackend.repository.OwnerRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;
    private final ModelMapper modelMapper;
    private final EmailService emailService;

    // 점주 정보 조회 (이메일 기준)
    public OwnerDTO getOwnerInfoByEmail(String ownerEmail) {
        OwnerEntity ownerEntity = ownerRepository.findByOwnerEmail(ownerEmail)
                .orElseThrow(() -> new RuntimeException("Owner not found with email: " + ownerEmail));
        return modelMapper.map(ownerEntity, OwnerDTO.class);
    }

    // 점주 정보 업데이트
    public OwnerEntity updateStoreInfo(Long ownerId, OwnerDTO ownerDTO) {
        OwnerEntity ownerEntity = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner not found with id: " + ownerId));

        ownerEntity.updateStoreInfo(
                ownerDTO.getStoreName(),
                ownerDTO.getDescription(),
                ownerDTO.getLocation(),
                ownerDTO.getImageUrl()
        );

        return ownerRepository.save(ownerEntity);
    }

    // 점주 회원가입
    public void joinOwner(JoinDTO joinDTO) {
        if (ownerRepository.existsByOwnerEmail(joinDTO.getOwnerEmail())) {
            throw new DataIntegrityViolationException("이미 등록된 이메일입니다.");
        }

        OwnerEntity ownerEntity = modelMapper.map(joinDTO, OwnerEntity.class);
        ownerRepository.save(ownerEntity);
    }

    // 로그인
    public OwnerEntity loginOwner(LoginDTO loginDTO) {
        OwnerEntity ownerEntity = ownerRepository.findByOwnerEmail(loginDTO.getOwnerEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!ownerEntity.getOwnerPassword().equals(loginDTO.getOwnerPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        return ownerEntity;
    }

    // 로그아웃
    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    // 이메일 중복 체크
    public boolean checkLoginEmailDuplicate(String ownerEmail) {
        return ownerRepository.existsByOwnerEmail(ownerEmail);
    }

    // 비밀번호 변경
    public void changeOwnerPassword(String ownerEmail, OwnerDTO ownerDTO) {
        OwnerEntity ownerEntity = ownerRepository.findByOwnerEmail(ownerEmail)
                .orElseThrow(() -> new RuntimeException("Owner not found with id: " + ownerEmail));

        if (!ownerEntity.getOwnerPassword().equals(ownerDTO.getOwnerPassword())) {
            throw new RuntimeException("Incorrect current password");
        }

        ownerEntity.setOwnerPassword(ownerDTO.getOwnerNewPassword());
        ownerRepository.save(ownerEntity);
    }

    // 회원 탈퇴
    public void deleteMember(Long ownerId) {
        ownerRepository.deleteById(ownerId);
    }

    // 임시 비밀번호 생성 메서드
    private String generateTemporaryPassword() {
        int length = 8;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            password.append(characters.charAt(random.nextInt(characters.length())));
        }
        return password.toString();
    }

    // 임시 비밀번호 이메일 전송
    public void sendTemporaryPassword(String ownerEmail) {
        // 사용자 조회
        OwnerEntity owner = ownerRepository.findByOwnerEmail(ownerEmail)
                .orElseThrow(() -> new IllegalArgumentException("해당 이메일을 가진 사용자가 없습니다."));

        // 임시 비밀번호 생성
        String temporaryPassword = generateTemporaryPassword();

        // 이메일로 임시 비밀번호 전송
        EmailDTO emailMessage = EmailDTO.builder()
                .to(ownerEmail)
                .subject("임시 비밀번호 발급 안내")
                .message("임시 비밀번호: " + temporaryPassword + "\n로그인 후 비밀번호를 변경하세요.")
                .build();
        emailService.sendMail(emailMessage);

        // 사용자 비밀번호 업데이트 (암호화 없이 그대로 저장)
        owner.setOwnerPassword(temporaryPassword);
        ownerRepository.save(owner);
    }
}
