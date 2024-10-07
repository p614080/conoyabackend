package com.sunny.conoyabackend.infomation;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId; // 이미지 ID

    private String imageUrl; // 이미지 URL
    private String description; // 이미지 설명

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false) // 방 ID에 대한 외래 키
    private Room room; // 연관된 Room 객체

    // 업데이트 메서드
    public void updateImageInfo(String imageUrl, String description) {
        this.imageUrl = imageUrl;
        this.description = description;
    }
}
