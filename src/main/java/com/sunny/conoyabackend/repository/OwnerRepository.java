package com.sunny.conoyabackend.repository;

import com.sunny.conoyabackend.entity.OwnerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface OwnerRepository extends JpaRepository<OwnerEntity, Long> {
    boolean existsByOwnerEmail(String ownerEmail);
    Optional<OwnerEntity> findByOwnerEmail(String ownerEmail);
    // 페이징 처리된 리스트 조회 (이미 제공됨)
    Page<OwnerEntity> findAll(Pageable pageable);
}
