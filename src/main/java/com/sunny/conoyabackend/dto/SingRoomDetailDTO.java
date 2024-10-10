package com.sunny.conoyabackend.dto;

import java.util.List;

public class SingRoomDetailDTO {
    private OwnerDTO owner;          // 점주 정보
    private List<RoomDTO> rooms;     // 점주가 소유한 노래방 방 정보 리스트

    // 기본 생성자
    public SingRoomDetailDTO() {}

    // 모든 필드를 초기화하는 생성자
    public SingRoomDetailDTO(OwnerDTO owner, List<RoomDTO> rooms) {
        this.owner = owner;
        this.rooms = rooms;
    }

    // Getter와 Setter 메서드들
    public OwnerDTO getOwner() {
        return owner;
    }

    public void setOwner(OwnerDTO owner) {
        this.owner = owner;
    }

    public List<RoomDTO> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomDTO> rooms) {
        this.rooms = rooms;
    }
}
