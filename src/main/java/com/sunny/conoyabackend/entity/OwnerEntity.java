package com.sunny.conoyabackend.entity;


import com.sunny.conoyabackend.domain.Favorites;
import com.sunny.conoyabackend.domain.Review;
import com.sunny.conoyabackend.domain.ReviewComment;
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

    @Column(nullable = false, length = 25, unique = true)
    private String ownerEmail; // 사업자 로그인아이디
    private String ownerNum; // 사업자 번호

    @Column(length = 25)
    private String ownerPassword; // 오너 비밀번호
    private String storeName; // 노래방 이름
    private String location; // 노래방위치 정보
    private String imageUrl; // 노래방이미지 URl

    @Lob
    private String description; // 노래방 정보




    @OneToMany(mappedBy = "ownerEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Favorites> favorites = new ArrayList<>();

    @OneToMany(mappedBy = "ownerEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "ownerEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ReviewComment> reviewComments = new ArrayList<>();

    @OneToMany(mappedBy = "ownerEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Room> rooms = new ArrayList<>();

    // 업데이트 메서드
    public void updateStoreInfo(String storeName, String description, String location, String imageUrl, String ownerNum) {
        if (storeName != null && !storeName.isEmpty()) {
            this.storeName = storeName;
        }
        if (description != null && !description.isEmpty()) {
            this.description = description;
        }
        if (location != null && !location.isEmpty()) {
            this.location = location;
        }
        if (imageUrl != null && !imageUrl.isEmpty()) {
            this.imageUrl = imageUrl;
        }
        if (ownerNum != null && !ownerNum.isEmpty()) {
            this.ownerNum = ownerNum;
        }

    }

    // password 에 대한 setter만 추가
    public void setOwnerPassword(String ownerPassword) {
        this.ownerPassword = ownerPassword;
    }

    // email 에 대한 setter만 추가
    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

}
