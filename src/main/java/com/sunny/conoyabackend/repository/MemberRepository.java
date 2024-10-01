package com.sunny.conoyabackend.repository;

import com.sunny.conoyabackend.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
