package com.example.karaoke;

import lombok.Data;

@Data
public class Room {
    private int roomNumber;      // 방 번호
    private boolean isAvailable;  // 방 사용 여부
    private int remainingTime;    // 남은 시간 (분 단위)
    private int remainingSongs;    // 남은 곡 수

    public Room(int roomNumber, boolean isAvailable, int remainingTime, int remainingSongs) {
        this.roomNumber = roomNumber;
        this.isAvailable = isAvailable;
        this.remainingTime = remainingTime;
        this.remainingSongs = remainingSongs;
    }
}
