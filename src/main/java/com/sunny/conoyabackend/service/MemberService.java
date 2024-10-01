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

    // 회원 정보 수정
    public Member updateMember(Long id, Member updateMember) {
        Optional<Member> existingMember = memberRepository.findById(id);
        if (existingMember.isPresent()) {
            Member member = existingMember.get();
            member.setNickname(updateMember.getNickname());
            member.setEmail(updateMember.getEmail());
            member.setPassword(updateMember.getPassword());
            return memberRepository.save(member);
        } else {
            throw new RuntimeException("Member not found");
        }
    }

//     // 회원 정보 삭제
//    public void deleteMember(Long id) {
//        memberRepository.deleteById(id);
//    }
}
