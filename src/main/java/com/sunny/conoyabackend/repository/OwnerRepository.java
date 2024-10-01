package com.sunny.conoyabackend.Repository;

import com.sunny.conoyabackend.Entity.Owner;
import com.sunny.conoyabackend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    boolean existByLoginEmail(String ownerEmail);
    boolean existByNickname(String nickname);
    Optional<Owner> findByLoginEmail(String ownerEmail);
}
