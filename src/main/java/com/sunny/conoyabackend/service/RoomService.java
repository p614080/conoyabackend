package com.sunny.conoyabackend.service;

import com.sunny.conoyabackend.dto.ReviewDTO;
import com.sunny.conoyabackend.dto.RoomDTO;

public interface RoomService {
    Long register(RoomDTO roomDTO);
    RoomDTO get(Long roomId);

    void remove(Long roomId);
}
