package com.sunny.conoyabackend.repository;




import com.sunny.conoyabackend.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    boolean existsByownerEmail(String ownerEmail);
    boolean existsByownerNickname(String ownerNickname);
    Optional<Owner> findByownerEmail(String ownerEmail);
}
