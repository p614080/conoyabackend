package com.sunny.conoyabackend.service;

import com.sunny.conoyabackend.dto.RoomDTO;
import com.sunny.conoyabackend.repository.RoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService{
    private final RoomRepository roomRepository;

    @Override
    public Long register(RoomDTO roomDTO){

        return 0L;
    }

    @Override
    public RoomDTO get(Long roomId){

        return null;
    }

    @Override
    public void remove(Long roomId){

    }
}
