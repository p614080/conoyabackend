package com.sunny.conoyabackend.infomation;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId; // 방 ID

    private int roomNumber; // 방 번호
    private boolean useroom; // 방 사용 여부
    private int roomposition; // 방 최대 인원
    private int remainingTime; // 남은 시간 (분 단위)
    private int remainingSongs; // 남은 곡 수
    private double roomRate; // 방 요금 (예: 시간당 요금)

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images; // 연관된 이미지 목록

    // 업데이트 메서드
    public void updateRoomInfo(boolean useroom, int roomposition, int remainingTime, int remainingSongs, double roomRate) {
        this.useroom = useroom;
        this.roomposition = roomposition;
        this.remainingTime = remainingTime;
        this.remainingSongs = remainingSongs;
        this.roomRate = roomRate;
    }

    // 방 사용 여부에 대한 setter만 추가
    public void setOccupied(boolean useroom) {
        this.useroom = useroom;
    }

    // 방 요금에 대한 setter 및 getter 추가
    public void setRoomRate(double roomRate) {
        this.roomRate = roomRate;
    }

    public double getRoomRate() {
        return roomRate;
    }
}
