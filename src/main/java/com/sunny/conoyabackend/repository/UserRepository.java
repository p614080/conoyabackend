package com.sunny.conoyabackend.repository;

import com.sunny.conoyabackend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByUserEmail(String userEmail);
    boolean existsByUserNickname(String userNickname);
    Optional<UserEntity> findByUserEmail(String userEmail);

}
