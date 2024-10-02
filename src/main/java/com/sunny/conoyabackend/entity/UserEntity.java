package com.sunny.conoyabackend.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String userEmail;
    private String userPassword;
    private String userNickname;



    // Nickname에 대한 setter만 추가
    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }
    // password에 대한 setter만 추가
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
