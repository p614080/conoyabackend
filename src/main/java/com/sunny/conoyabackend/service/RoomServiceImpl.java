package com.sunny.conoyabackend.service;

import com.sunny.conoyabackend.dto.RoomDTO;
import com.sunny.conoyabackend.entity.Room;
import com.sunny.conoyabackend.repository.RoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
    public boolean isUse(Long roomId){
        LocalDateTime currentTime = LocalDateTime.now();
        int minutesDifferent;
        Optional<Room> result = roomRepository.findById(roomId);
        Room room = result.orElseThrow();
        if(!room.isUseroom()){
            return false;
        }else{
            if(room.getPaymentTime()==0){
                room.setUseroom(true);
                roomRepository.save(room);
                return true;
            }
            else{
                minutesDifferent = (int) ChronoUnit.MINUTES.between(room.getStartTime(), currentTime);
                if(minutesDifferent < room.getPaymentTime()){
                    room.setUseroom(true);
                    roomRepository.save(room);
                    return true;
                }
                else{
                    room.setUseroom(false);
                    room.setPaymentTime(0);
                    room.setStartTime(null);
                    roomRepository.save(room);
                    return false;
                }
            }
        }
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
    public void insertTime(RoomDTO roomDTO){
        Optional<Room> result = roomRepository.findById(roomDTO.getRoomId());
        Room room = result.orElseThrow();
        room.setStartTime(LocalDateTime.now());
        room.setPaymentTime(roomDTO.getPaymentTime());
        room.setUseroom(true);
        roomRepository.save(room);
    }
    @Override
    public void insertCoin(RoomDTO roomDTO){
        Optional<Room> result = roomRepository.findById(roomDTO.getRoomId());
        Room room = result.orElseThrow();
        room.setStartTime(LocalDateTime.now());
        room.setPaymentCoin(roomDTO.getPaymentCoin());
        room.setUseroom(true);
        roomRepository.save(room);
    }

    @Override
    public void remove(Long roomId){
        roomRepository.deleteById(roomId);
    }

    @Override
    public List<RoomDTO> getRoomsByOwnerId(Long ownerId) {
        isUse(ownerId);
        List<Room> rooms = roomRepository.findAllByOwnerEntity_OwnerId(ownerId);

        // 엔티티 목록을 DTO 목록으로 변환
        return rooms.stream()
                .map(room -> modelMapper.map(room, RoomDTO.class))
                .collect(Collectors.toList());
    }
}
