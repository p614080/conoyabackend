package com.sunny.conoyabackend.controller;

import com.sunny.conoyabackend.domain.Member;
import com.sunny.conoyabackend.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberController {
    @Autowired
    private MemberService memberService;

    // 이메일로 로그인
//    @PostMapping("/login")
//    public ResponseEntity<Member> login(@RequestParam String email, @RequestParam String password) {
//        Member loggedInMember = memberService.login(email, password);
//        return ResponseEntity.ok(loggedInMember); // 로그인 성공 시 회원 정보 반환
//    }

    // 닉네임 변경
    @PutMapping("/{id}/nickname")
    public ResponseEntity<Member> updateNickname(@PathVariable Long id, @RequestParam String nickname) {
        Member updatedMember = memberService.updateNickname(id, nickname);
        return ResponseEntity.ok(updatedMember);
    }

    // 비밀번호 변경
    @PutMapping("/{id}/password")
    public ResponseEntity<Member> changePassword(@PathVariable Long id, @RequestParam String oldPassword, @RequestParam String newPassword) {
        Member updatedMember = memberService.changePassword(id, oldPassword, newPassword);
        return ResponseEntity.ok(updatedMember);
    }

    // 회원 탈퇴
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }
}
