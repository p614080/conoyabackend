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

    // 회원 정보 수정
    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(
            @PathVariable
            Long id,
            @RequestBody
            Member updateMember) {
        Member member = memberService.updateMember(id, updateMember);
        return ResponseEntity.ok(member);
    }

//    // 회원 정보 삭제
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
//        memberService.deleteMember(id);
//        return ResponseEntity.noContent().build();
//    }
}
