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






    // Nickname에 대한 setter만 추가
    public void setOwnerPassword(String ownerPassword) {
        this.ownerPassword = ownerPassword;
    }
}
