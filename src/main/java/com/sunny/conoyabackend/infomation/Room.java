package com.sunny.conoyabackend.infomation;

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
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId; // 방 ID

    private int roomNumber; // 방 번호
    private boolean useroom; // 방 사용 여부
    private int remainingTime; // 남은 시간 (분 단위)
    private int remainingSongs; // 남은 곡 수

    // 업데이트 메서드
    public void updateRoomInfo(boolean useroom, int remainingTime, int remainingSongs) {
        this.useroom = useroom;
        this.remainingTime = remainingTime;
        this.remainingSongs = remainingSongs;
    }

    // 방 사용 여부에 대한 setter만 추가
    public void setOccupied(boolean useroom) {
        this.useroom = useroom;
    }
}