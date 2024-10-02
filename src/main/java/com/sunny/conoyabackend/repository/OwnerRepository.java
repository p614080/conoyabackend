package com.sunny.conoyabackend.repository;




import com.sunny.conoyabackend.entity.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<OwnerEntity, Long> {
    boolean existsByOwnerEmail(String ownerEmail);
    boolean existsByStoreName(String storeName);
    Optional<OwnerEntity> findByOwnerEmail(String ownerEmail);
}
