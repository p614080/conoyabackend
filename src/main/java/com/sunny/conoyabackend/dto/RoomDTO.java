package com.sunny.conoyabackend.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {

    private Long roomId; // 방 ID
    private int roomNumber; // 방 번호
    private boolean useroom; // 방 사용 여부
    private int roomSize; // 방 최대 인원
    private LocalDateTime startTime; // 시작 시간
    private int paymentTime; // 시간으로 결제
    private int paymentCoin; // 곡 수로 결제
    private String roomRate; // 방 요금
    private List<ImageDTO> images; // 이미지 목록
    private OwnerDTO ownerEntity; // 점주 정보
}
