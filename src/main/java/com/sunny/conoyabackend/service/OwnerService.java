package com.sunny.conoyabackend.service;


import com.sunny.conoyabackend.dto.*;
import com.sunny.conoyabackend.entity.OwnerEntity;
import com.sunny.conoyabackend.repository.OwnerRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;
    private final EmailService emailService;

//    public boolean checkLoginEmailDuplicate(LoginDTO ownerEmail) {
//        return ownerRepository.existsByOwnerEmail(String.valueOf(ownerEmail));
//    }
    // 이메일 중복체크
    public boolean checkLoginEmailDuplicate(String ownerEmail) {
        return ownerRepository.existsByOwnerEmail(ownerEmail);
    }

    //회원가입
    public void join2(JoinDTO ownerReq) {
        ownerRepository.save(ownerReq.ownerEntity());
    }

    // 로그인
    public OwnerEntity login2(LoginDTO ownerReq) {
        Optional<OwnerEntity> optionalOwner = ownerRepository.findByOwnerEmail(ownerReq.getOwnerEmail());

        if(optionalOwner.isEmpty()) {
            return null;
        }

        OwnerEntity ownerEntity = optionalOwner.get();

        if (!ownerEntity.getOwnerPassword().equals(ownerReq.getOwnerPassword())) {
            return null;
        }
        return ownerEntity;
    }
    // 로그아웃
    public OwnerEntity logout(HttpServletRequest request, OwnerEntity owner) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // 세션 무효화
        }
        return owner; // 로그아웃된 사용자 정보를 반환할 수 있습니다.
    }


    // 비밀번호 변경
    public OwnerEntity changePassword(Long ownerId, OwnerDTO passwordOwnerDTO) {
        OwnerEntity owner = ownerRepository.findById(ownerId).orElseThrow(() -> new RuntimeException("user not found"));
        // 기존 비밀번호 확인
        if (!owner.getOwnerPassword().equals(passwordOwnerDTO.getOwnerPassword())) {
            throw new RuntimeException("Incorrect old password");
        }
        // 새 비밀번호 설정
        owner.setOwnerPassword(passwordOwnerDTO.getOwnerNewPassword());

        // 엔티티 저장 후 반환
        return ownerRepository.save(owner);
    }

    //  회원 탈퇴
    public void deleteMember(Long ownerId) {
        ownerRepository.deleteById(ownerId);

    }

    // 회원 업데이트
    public OwnerEntity updateStoreInfo(Long ownerId, OwnerDTO ownerDTO) {
        // 1. 저장소 정보 조회
        OwnerEntity ownerEntity = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner not found with id: " + ownerId));

        // 2. 점주 정보 수정
        ownerEntity.updateStoreInfo(
                ownerDTO.getStoreName(),
                ownerDTO.getDescription(),
                ownerDTO.getLocation(),
                ownerDTO.getImageUrl()
        );

        // 3. 수정된 정보 저장
        return ownerRepository.save(ownerEntity);
    }

    // 임시 비밀번호 생성
    public String generateTemporaryPassword() {
        int length = 8;  // 임시 비밀번호 길이 설정
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            password.append(characters.charAt(random.nextInt(characters.length())));
        }
        return password.toString();
    }

    // 사업자 비밀번호 찾기
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
        owner.setOwnerPassword(temporaryPassword); // 암호화 없이 그대로 저장
        ownerRepository.save(owner);
    }

    // 점주 목록 가져오기
    public PageResponseDTO<OwnerDTO> list(PageRequestDTO pageRequestDTO) {
        int page = pageRequestDTO.getPage() - 1; // 페이지는 0부터 시작
        int size = pageRequestDTO.getSize();

        // 데이터베이스에서 점주 목록 가져오기
        List<OwnerEntity> owners = ownerRepository.findAll().stream()
                .skip(page * size) // 페이징 처리
                .limit(size) // 페이지 크기만큼 제한
                .collect(Collectors.toList());

        // DTO 변환
        List<OwnerDTO> ownerDTOs = owners.stream()
                .map(owner -> OwnerDTO.builder()
                        .storeName(owner.getStoreName())
                        .location(owner.getLocation())
                        .build())
                .collect(Collectors.toList());

        // 반환할 PageResponseDTO 생성
        return new PageResponseDTO<>(ownerDTOs, pageRequestDTO, ownerRepository.count());
    }
}


