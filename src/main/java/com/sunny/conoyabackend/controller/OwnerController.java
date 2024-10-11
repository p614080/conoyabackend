package com.sunny.conoyabackend.controller;

import com.sunny.conoyabackend.dto.*;
import com.sunny.conoyabackend.entity.OwnerEntity;
import com.sunny.conoyabackend.service.OwnerService;
import com.sunny.conoyabackend.service.RoomService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/owners")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private RoomService roomService;


    // 점주 정보 조회
    @GetMapping("/mypage")
    public ResponseEntity<OwnerDTO> getOwnerInfo(String ownerEmail) {
        try {
            OwnerDTO ownerInfo = ownerService.getOwnerInfoByEmail(ownerEmail);
            return ResponseEntity.ok(ownerInfo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // 노래방 정보 변경
    @PutMapping("store-info")
    public ResponseEntity<OwnerEntity> updateStoreInfo(
            @PathVariable Long id,
            @RequestBody OwnerDTO ownerDTO) {
        OwnerEntity updatedOwner = ownerService.updateStoreInfo(id, ownerDTO);
        return ResponseEntity.ok(updatedOwner);
    }

    // 회원가입 API
    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody OwnerDTO ownerDTO, BindingResult bindingResult) {
        if (!ownerDTO.getOwnerPassword().equals(ownerDTO.getNewPassword())) {
            return new ResponseEntity<>("비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
        }
        try {
            ownerService.joinOwner(ownerDTO);
            return new ResponseEntity<>("회원가입 되셨습니다.", HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("이미 등록된 이메일입니다.", HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>("서버 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<OwnerEntity> login(@RequestBody LoginDTO loginDTO) {
        OwnerEntity loggedInMember = ownerService.loginOwner(loginDTO);
        return ResponseEntity.ok(loggedInMember);
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        ownerService.logout(request);
        return ResponseEntity.noContent().build();
    }

    // 이메일 중복 체크
    @GetMapping("/check-email")
    public ResponseEntity<String> checkEmail(@RequestParam String ownerEmail) {
        boolean isDuplicate = ownerService.checkLoginEmailDuplicate(ownerEmail);
        if (isDuplicate) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 사용 중인 이메일입니다.");
        }
        return ResponseEntity.ok("사용 가능한 이메일입니다.");
    }

    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody OwnerDTO ownerDTO) {
        try {
            // ownerEmail, 현재 비밀번호, 새 비밀번호를 DTO로 받음
            ownerService.changeOwnerPassword(ownerDTO);
            return ResponseEntity.ok("비밀번호가 성공적으로 변경되었습니다.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("비밀번호 변경 중 오류가 발생했습니다.");
        }
    }
    // 회원 탈퇴
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        ownerService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }

    // 점주 정보 및 노래방 방 리스트 가져오기
    @GetMapping("/detail/{ownerId}")
    public ResponseEntity<SingRoomDetailDTO> getSingRoomDetail(@PathVariable(name = "ownerId") Long ownerId) {
        log.info("Retrieving details for owner ID: {}", ownerId);

        try {
            // 1. 노래방 정보가 들어있는 점주의 데이터를 가져온다.
            OwnerDTO ownerDTO = ownerService.get(ownerId);

            // 2. 점주의 데이터 안에 있는 roomId로 room 정보를 가져온다.
            List<RoomDTO> roomDTOList = roomService.getRoomsByOwnerId(ownerId);

            // 3. 점주 정보와 방 정보를 합쳐서 SingroomDTO 객체를 생성한다.
            SingRoomDetailDTO singroomDTO = new SingRoomDetailDTO(ownerDTO, roomDTOList);

            // 4. SingroomDTO 객체를 반환한다.
            return ResponseEntity.ok(singroomDTO);
        } catch (RuntimeException e) {
            log.error("Failed to retrieve details for owner ID: {}. Error: {}", ownerId, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            log.error("An unexpected error occurred while retrieving details for owner ID: {}. Error: {}", ownerId, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("/list")
    public ResponseEntity<PageResponseDTO<OwnerDTO>> list(PageRequestDTO pageRequestDTO) {
        log.info(pageRequestDTO.toString());
        PageResponseDTO<OwnerDTO> response = ownerService.list(pageRequestDTO);
        return ResponseEntity.ok(response);
    }

}
