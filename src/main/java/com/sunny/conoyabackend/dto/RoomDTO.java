package com.sunny.conoyabackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {
    
    private Long roomId; // 방 ID
    private int roomNumber; // 방 번호
    private boolean useroom; // 방 사용 여부
    private int roomposition; // 방 최대 인원
    private int remainingTime; // 남은 시간 (분 단위)
    private int remainingSongs; // 남은 곡 수
    private double roomRate; // 방 요금
}