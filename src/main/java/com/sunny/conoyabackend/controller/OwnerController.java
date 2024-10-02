package com.sunny.conoyabackend.controller;

import com.sunny.conoyabackend.dto.LoginDTO;
import com.sunny.conoyabackend.dto.OwnerDTO;
import com.sunny.conoyabackend.entity.OwnerEntity;
import com.sunny.conoyabackend.service.OwnerService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/owners")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    // 노래방 정보 변경
    @PutMapping("/{id}/store-info")
    public ResponseEntity<OwnerEntity> updateStoreInfo(
            @PathVariable Long id,
            @RequestBody OwnerDTO ownerDTO) {
        OwnerEntity updatedOwner = ownerService.updateStoreInfo(id, ownerDTO);
        return ResponseEntity.ok(updatedOwner);
    }


    // 로그인
    @PostMapping("/login")
    public ResponseEntity<OwnerEntity> login(@RequestBody LoginDTO loginDTO) {
        OwnerEntity loggedInMember = ownerService.login2(loginDTO);
        return ResponseEntity.ok(loggedInMember); //
    }

    // 로그아웃
    @PostMapping("/logout")
    public OwnerEntity logout(HttpServletRequest request, @RequestBody OwnerEntity owner) {
        return ownerService.logout(request, owner);
    }

    // 비밀번호 변경
    @PutMapping("/{id}/password")
    public ResponseEntity<OwnerEntity> changePassword(@PathVariable Long ownerId, @RequestParam OwnerDTO passwordOwnerDTO) {
        OwnerEntity updatedOwnerEntity = ownerService.changePassword(ownerId, passwordOwnerDTO);
        return ResponseEntity.ok(updatedOwnerEntity);
    }

    // 회원 탈퇴
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember (@PathVariable Long ownerId) {
        ownerService.deleteMember(ownerId);
        return ResponseEntity.noContent().build();
    }

}
