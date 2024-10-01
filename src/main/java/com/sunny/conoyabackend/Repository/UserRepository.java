package com.sunny.conoyabackend.Repository;

import com.sunny.conoyabackend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existByLoginEmail(String userEmail);
    boolean existByNickname(String nickname);
    Optional<User> findByLoginEmail(String loginEmail);

}
