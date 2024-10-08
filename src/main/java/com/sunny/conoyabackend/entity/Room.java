package com.sunny.conoyabackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId; // 방 ID

    private int roomNumber; // 방 번호
    @Builder.Default
    private boolean useroom = false; // 방 사용 여부
    private int roomSize; // 방 최대 인원
    private LocalDateTime startTime;
    @Builder.Default
    private int paymentTime = 0; // 시간으로 결제
    @Builder.Default
    private int paymentCoin = 0; // 곡 수로 결제
    private String roomRate; // 방 요금

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images; // 연관된 이미지 목록

    @OneToOne(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private OwnerEntity ownerEntity;
}
