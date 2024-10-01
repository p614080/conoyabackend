package com.sunny.conoyabackend.service;


import com.sunny.conoyabackend.dto.JoinRequest;
import com.sunny.conoyabackend.dto.LoginRequest;
import com.sunny.conoyabackend.entity.Owner;
import com.sunny.conoyabackend.entity.User;
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

    public void join2(JoinRequest ownerReq) {
        ownerRepository.save(ownerReq.ownerEntity());
    }

    public Owner login2(LoginRequest ownerReq) {
        Optional<Owner> optionalOwner = ownerRepository.findByOwnerNum(ownerReq.getOwnerNum());

        if(optionalOwner.isEmpty()) {
            return null;
        }

        Owner owner = optionalOwner.get();

        if (!owner.getOwnerPassword().equals(ownerReq.getOwnerPassword())) {
            return null;
        }
        return owner;
    }



    // 비밀번호 변경
    public Owner changePassword(Long ownerNum, String oldPassword, String newPassword) {
        Owner owner = ownerRepository.findById(ownerNum).orElseThrow(() -> new RuntimeException("owner not found"));
        if (!owner.getOwnerPassword().equals(oldPassword)) {
            throw new RuntimeException("Incorrect old password");
        }
        owner.setOwnerPassword(newPassword);
        return ownerRepository.save(owner);
    }

    //  회원 탈퇴
    public void deleteMember(Long ownerNum) {
        ownerRepository.deleteById(ownerNum);

    }

    // 회원 업데이트
    public Owner updateStoreInfo(Long ownerId, String storeName, String description, String location, String imageurl) {

        // 1.저장소 정보 조회
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("저장소를 찾을 수 없습니다."));

        // 2. 정보 수정
//        if (storeName != null && !storeName.isEmpty()) {
//            ownerRepository.setStoreName(storeName);
//        }
//        if (description != null && !description.isEmpty()) {
//            ownerRepository.setDescription(description);
//        }
//        if (location != null && !location.isEmpty()) {
//            ownerRepository.setLocation(location);
//        }
//        if (imageUrl != null && !imageUrl.isEmpty()) {
//            ownerRepository.setImageUrl(imageUrl);
//        }

        // 3. 수정된 정보 저장
        return ownerRepository.save(owner);
    }

}
