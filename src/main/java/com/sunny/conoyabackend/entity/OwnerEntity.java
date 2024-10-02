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
public class OwnerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ownerId;

    private String ownerNum; // 사업자 번호
    private String ownerPassword; // 오너 비밀번호
    private String storeName; // 노래방 이름
    private String location; // 노래방위치 정보
    private String imageUrl; // 노래방이미지 URl
    private String description; // 노래방 정보

    @OneToMany(mappedBy = "ownerEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Favorites> favorites = new ArrayList<>();




    // Nickname에 대한 setter만 추가
    public void setOwnerPassword(String ownerPassword) {
        this.ownerPassword = ownerPassword;
    }
}
