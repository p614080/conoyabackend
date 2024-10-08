package com.sunny.conoyabackend.service;

import com.sunny.conoyabackend.dto.RoomDTO;
import com.sunny.conoyabackend.entity.Room;
import com.sunny.conoyabackend.repository.RoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService{
    private final ModelMapper modelMapper;
    private final RoomRepository roomRepository;

    @Override
    public Long register(RoomDTO roomDTO){
        Room room = modelMapper.map(roomDTO, Room.class);
        Room result = roomRepository.save(room);
        return result.getRoomId();
    }

    @Override
    public RoomDTO get(Long roomId){
        Optional<Room> result = roomRepository.findById(roomId);
        Room room = result.orElseThrow();
        RoomDTO roomDTO = modelMapper.map(room, RoomDTO.class);
        return roomDTO;
    }

    @Override
    public void modify(RoomDTO roomDTO){
        Optional<Room> result = roomRepository.findById(roomDTO.getRoomId());
        Room room = result.orElseThrow();
        room.setRoomNumber(roomDTO.getRoomNumber());
        room.setRoomSize(roomDTO.getRoomSize());
        room.setRoomRate(roomDTO.getRoomRate());
        roomRepository.save(room);
    }

    @Override
    public void remove(Long roomId){
        roomRepository.deleteById(roomId);
    }

    @Override
    public List<RoomDTO> getRoomsByOwnerId(Long ownerId) {
        List<Room> rooms = roomRepository.findAllByOwnerEntity_OwnerId(ownerId);

        // 엔티티 목록을 DTO 목록으로 변환
        return rooms.stream()
                .map(room -> modelMapper.map(room, RoomDTO.class))
                .collect(Collectors.toList());
    }
}
