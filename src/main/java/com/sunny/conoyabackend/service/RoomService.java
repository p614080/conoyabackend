package com.sunny.conoyabackend.service;

import com.sunny.conoyabackend.dto.ReviewDTO;
import com.sunny.conoyabackend.dto.RoomDTO;

import java.util.List;

public interface RoomService {
    Long register(RoomDTO roomDTO);
    RoomDTO get(Long roomId);

    boolean isUse(Long roomId);
    void modify(RoomDTO roomDTO);
    void insertTime(RoomDTO roomDTO, int time);
    void insertCoin(RoomDTO roomDTO, int coin);
    void decreaseCoin(RoomDTO roomDTO);
    void remove(Long roomId);
    List<RoomDTO> getRoomsByOwnerId(Long ownerId);
}
