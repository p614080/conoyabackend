package com.sunny.conoyabackend.controller;

import com.sunny.conoyabackend.dto.JoinDTO;
import com.sunny.conoyabackend.dto.LoginDTO;
import com.sunny.conoyabackend.dto.UserDTO;
import com.sunny.conoyabackend.entity.UserEntity;
import com.sunny.conoyabackend.service.UserService;
import com.sunny.conoyabackend.service.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserServiceImpl userServiceImpl;


    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody UserDTO userDTO, BindingResult bindingResult) {
        // 비밀번호 일치 검증
//        if (!userDTO.getUserPassword().equals(userDTO.getUserPasswordCheck())) {
//            bindingResult.rejectValue("userPassword", "userPasswordCheck", "2개의 패스워드가 일치하지 않습니다.");
//            return ResponseEntity.badRequest().body("비밀번호가 일치하지 않습니다."); // 400 Bad Request
//        }

        // 이미 등록된 사용자 처리
        try {
            userService.join(userDTO);
            return new ResponseEntity<>("User successfully registered", HttpStatus.CREATED);  // 성공 메시지와 상태 코드 반환
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 등록된 사용자입니다."); // 409 Conflict
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("가입 중 오류가 발생했습니다.");
        }
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        UserEntity loggedInMember = userService.login(userDTO);
        if (loggedInMember == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("이메일 또는 비밀번호가 잘못되었습니다."); // 401 Unauthorized
        }
        return ResponseEntity.ok(loggedInMember); // 로그인 성공 시 회원 정보 반환
    }
    @GetMapping("/info")
    public ResponseEntity<UserDTO> getUserInfo(@RequestParam String userEmail) {
        UserDTO userInfo = userService.getUserByEmail(userEmail);
        return ResponseEntity.ok(userInfo);
    }

    @PutMapping("/updateNickname")
    public ResponseEntity<String> updateNickname(@RequestBody UserDTO userDTO) {
        userService.updateNickname(userDTO.getUserEmail(), userDTO.getUserNickname());
        return ResponseEntity.ok("닉네임이 성공적으로 업데이트되었습니다.");
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<String> updatePassword(@RequestBody UserDTO userDTO) {
        userService.updatePassword(userDTO.getUserEmail(), userDTO.getUserPassword());
        return ResponseEntity.ok("비밀번호가 성공적으로 업데이트되었습니다.");
    }
}



