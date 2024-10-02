package com.sunny.conoyabackend.entity;


import com.sunny.conoyabackend.domain.Favorites;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, length = 25, unique = true)
    private String userEmail;

    @Column(length = 25)
    private String userPassword;

    @Column(nullable = false, length = 25, unique = true)
    private String userNickname;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Favorites> favorites = new ArrayList<>();

    // Nickname에 대한 setter만 추가
    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }
    // password에 대한 setter만 추가
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
