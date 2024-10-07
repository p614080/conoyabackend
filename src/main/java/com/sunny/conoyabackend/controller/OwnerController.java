package com.sunny.conoyabackend.controller;

import com.sunny.conoyabackend.dto.JoinDTO;
import com.sunny.conoyabackend.dto.LoginDTO;
import com.sunny.conoyabackend.dto.OwnerDTO;
import com.sunny.conoyabackend.entity.OwnerEntity;
import com.sunny.conoyabackend.service.OwnerService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    /**
     * 회원가입 API
     * @param joinDTO 회원가입 요청 데이터
     * @return HTTP 상태 코드
     */
    @PostMapping("/join2")
    public ResponseEntity<String> join(@RequestBody JoinDTO joinDTO, BindingResult bindingResult) {
        ownerService.join2(joinDTO);  // 회원가입 서비스 호출
        // 비밀번호 일치 검증
        if (!joinDTO.getOwnerPassword().equals(joinDTO.getOwnerPasswordCheck())) {
            bindingResult.rejectValue("password", "passwordCheck",
                    "2개의 패스워드가 일치하지 않습니다.");
        }
        // 이미 등록된 사용자
        try {
            ownerService.join2(joinDTO);
        }catch(DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");

        }catch(Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());

        }
        return new ResponseEntity<>("User successfully registered", HttpStatus.CREATED);  // 성공 메시지와 상태 코드 반환
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
    // 이메일 중복 체크 API
    @GetMapping("/check-email")
    public ResponseEntity<?> checkEmail(@RequestParam String ownerEmail) {
        try {
            // 중복 여부 확인
            boolean isDuplicate = ownerService.checkLoginEmailDuplicate(ownerEmail);
            return ResponseEntity.ok(isDuplicate);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error checking email duplication");
        }
    }
    // 가게이름 중복 체크 API
    @GetMapping("/check-storename")
    public ResponseEntity<?> checkStoreName(@RequestParam String storeName) {
        try {
            // 유효성 검증
            if (storeName == null || storeName.trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Invalid store name");
            }

            // 중복 여부 확인
            boolean isDuplicate = ownerService.checkStoreNameDuplicate(storeName);
            return ResponseEntity.ok(isDuplicate);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error checking store name duplication");
        }
    }

    // 닉네임 변경
    @PutMapping("/{id}/storename")
    public ResponseEntity<OwnerEntity> updateNickname(@PathVariable Long userId, @RequestParam OwnerDTO storeName ) {
        OwnerEntity updateStoreInfo = ownerService.updateStoreInfo(userId, storeName);
        return ResponseEntity.ok(updateStoreInfo);
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
