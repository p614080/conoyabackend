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

    // 점주 리스트 가져오기
    @GetMapping("/list")
    public ResponseEntity<PageResponseDTO<OwnerDTO>> list(PageRequestDTO pageRequestDTO) {
        log.info(pageRequestDTO.toString());
        PageResponseDTO<OwnerDTO> response = ownerService.list(pageRequestDTO);
        return ResponseEntity.ok(response);
    }

    // 점주 정보 (노래방 방 리스트 가지고 있는 정보)
    @GetMapping("/detail/{ownerId}")
    public SingRoomDetailDTO get(@PathVariable(name = "ownerId") Long ownerId) {
        System.out.println(ownerId);
        // 1. 노래방 정보가 들어있는 점주의 데이터를 가져온다.
        OwnerDTO ownerDTO = ownerService.get(ownerId);
        // 2. 점주의 데이터 안에 있는 roomId로 room 정보를 가져온다.
        List<RoomDTO> roomDTOList = roomService.getRoomsByOwnerId(ownerId);
        // 3. 점주 정보와 방 정보를 합쳐서 SingroomDTO 객체를 생성한다.
        SingRoomDetailDTO singroomDTO = new SingRoomDetailDTO(ownerDTO, roomDTOList);
        // 4. SingroomDTO 객체를 반환한다.
        return singroomDTO;
    }

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
        // 비밀번호 일치 검증
        if (!joinDTO.getOwnerPassword().equals(joinDTO.getOwnerPasswordCheck())) {
            bindingResult.rejectValue("ownerPassword", "ownerPasswordCheck", "2개의 패스워드가 일치하지 않습니다.");
            return new ResponseEntity<>("비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
        }

        try {
            // 회원가입 서비스 호출
            ownerService.join2(joinDTO);
            return new ResponseEntity<>("User successfully registered", HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            // 중복된 사용자 예외 처리
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            return new ResponseEntity<>("이미 등록된 이메일입니다.", HttpStatus.CONFLICT);
        } catch (Exception e) {
            // 기타 예외 처리
            bindingResult.reject("signupFailed", e.getMessage());
            return new ResponseEntity<>("서버 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
        boolean isDuplicate = ownerService.checkLoginEmailDuplicate(ownerEmail);
        if (isDuplicate) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 사용 중인 이메일입니다.");
        }
        return ResponseEntity.ok("사용 가능한 이메일입니다.");
    }


    // 점포명 변경
    @PutMapping("/{id}/store-name")
    public ResponseEntity<OwnerEntity> updateStoreName(@PathVariable Long userId, @RequestParam OwnerDTO storeName ) {
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

