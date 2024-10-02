package com.sunny.conoyabackend.service;


import com.sunny.conoyabackend.dto.JoinDTO;
import com.sunny.conoyabackend.dto.LoginDTO;
import com.sunny.conoyabackend.dto.OwnerDTO;
import com.sunny.conoyabackend.entity.OwnerEntity;
import com.sunny.conoyabackend.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;

    public boolean checkLoginIdDuplicate2(String ownerNum) {
        return ownerRepository.existsByOwnerNum(ownerNum);
    }

    public boolean checkNicknameDuplicate2(String storeName) {
        return ownerRepository.existsByStoreName(storeName);
    }

    public void join2(JoinDTO ownerReq) {
        ownerRepository.save(ownerReq.ownerEntity());
    }

    public OwnerEntity login2(LoginDTO ownerReq) {
        Optional<OwnerEntity> optionalOwner = ownerRepository.findByOwnerNum(ownerReq.getOwnerNum());

        if(optionalOwner.isEmpty()) {
            return null;
        }

        OwnerEntity ownerEntity = optionalOwner.get();

        if (!ownerEntity.getOwnerPassword().equals(ownerReq.getOwnerPassword())) {
            return null;
        }
        return ownerEntity;
    }



    // 비밀번호 변경
    public OwnerEntity changePassword(Long ownerNum, String oldPassword, String newPassword) {
        OwnerEntity ownerEntity = ownerRepository.findById(ownerNum).orElseThrow(() -> new RuntimeException("owner not found"));
        if (!ownerEntity.getOwnerPassword().equals(oldPassword)) {
            throw new RuntimeException("Incorrect old password");
        }
        ownerEntity.setOwnerPassword(newPassword);
        return ownerRepository.save(ownerEntity);
    }

    //  회원 탈퇴
    public void deleteMember(Long ownerNum) {
        ownerRepository.deleteById(ownerNum);

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

}
