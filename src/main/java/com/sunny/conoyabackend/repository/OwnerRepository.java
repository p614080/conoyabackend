package com.sunny.conoyabackend.repository;

import com.sunny.conoyabackend.entity.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OwnerRepository extends JpaRepository<OwnerEntity, Long> {
    boolean existsByOwnerEmail(String ownerEmail);
    Optional<OwnerEntity> findByOwnerEmail(String ownerEmail);
    List<OwnerEntity> findAll();
}
