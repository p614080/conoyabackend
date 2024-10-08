package com.sunny.conoyabackend.service;

import com.sunny.conoyabackend.dto.RoomDTO;
import com.sunny.conoyabackend.entity.OwnerEntity;
import com.sunny.conoyabackend.entity.Room;
import com.sunny.conoyabackend.repository.OwnerRepository;
import com.sunny.conoyabackend.repository.RoomRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoomServiceTests {
    @Autowired
    private RoomService roomService;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private RoomRepository roomRepository;

    private static final Logger log = LoggerFactory.getLogger(RoomServiceTests.class);

    @Test
    @DisplayName("시간 추가 테스트")
    public void test_time(){
        RoomDTO roomDTO = roomService.get(1L);
        roomService.insertTime(roomDTO, 3);
    }

    @Test
    @DisplayName("사용 여부 테스트")
    public void test_is_use() {
        log.info("====사용 여부 테스트====");
        boolean isUsed = roomService.isUse(1L);
        log.info("Room 1 사용 여부: {}", isUsed); // boolean 값을 문자열로 출력
    }

    @Test
    @DisplayName("코인 추가 테스트")
    public void test_coin(){
        RoomDTO roomDTO = roomService.get(7L);
        roomService.insertCoin(roomDTO, 4);
    }

    @Test
    @DisplayName("코인 추가 테스트")
    public void test_decrease_coin(){
        RoomDTO roomDTO = roomService.get(7L);
        roomService.decreaseCoin(roomDTO);
        roomService.decreaseCoin(roomDTO);
    }
}