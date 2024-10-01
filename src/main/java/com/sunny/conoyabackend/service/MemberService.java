package com.sunny.conoyabackend.service;

import com.sunny.conoyabackend.domain.Member;
import com.sunny.conoyabackend.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    // 이메일로 로그인
    public Member login(String email, String password) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Member not found with email: " + email));

        if(!member.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        return member;
    }

    // 닉네임 변경
    public Member updateNickname(Long id, String newNickname) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new RuntimeException("Member not found"));
        member.setNickname(newNickname);
        return memberRepository.save(member);
        }

    // 비밀번호 변경
    public Member changePassword(Long id, String oldPassword, String newPassword) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new RuntimeException("Member not found"));
        if (!member.getPassword().equals(oldPassword)) {
            throw new RuntimeException("Incorrect old password");
        }
        member.setPassword(newPassword);
        return memberRepository.save(member);
    }

//     // 회원 탈퇴
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }
}
