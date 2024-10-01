package com.sunny.conoyabackend.repository;

import com.sunny.conoyabackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUserEmail(String userEmail);
    boolean existsByUserNickname(String userNickname);
    Optional<User> findByUserEmail(String userEmail);

}
