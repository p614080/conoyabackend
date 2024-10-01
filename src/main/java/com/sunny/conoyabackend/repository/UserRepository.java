package com.sunny.conoyabackend.repository;

import com.sunny.conoyabackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existByLoginEmail(String userEmail);
    boolean existByNickname(String nickname);
    Optional<User> findByLoginEmail(String loginEmail);

}
