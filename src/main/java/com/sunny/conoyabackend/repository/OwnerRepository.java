package com.sunny.conoyabackend.repository;




import com.sunny.conoyabackend.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    boolean existsByOwnerEmail(String ownerEmail);
    boolean existsByOwnerNickname(String ownerNickname);
    Optional<Owner> findByOwnerEmail(String ownerEmail);
}
