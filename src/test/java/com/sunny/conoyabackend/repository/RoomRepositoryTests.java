package com.sunny.conoyabackend.repository;


import com.sunny.conoyabackend.dto.RoomDTO;
import com.sunny.conoyabackend.entity.OwnerEntity;
import com.sunny.conoyabackend.entity.Room;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import java.util.HashMap;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoomRepositoryTests {
    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger log = LoggerFactory.getLogger(RoomRepositoryTests.class);

    @Test
    @DisplayName("방 추가 테스트")
    public void test_time() {
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);

        transactionTemplate.execute(new TransactionCallback<Void>() {
            @Override
            public Void doInTransaction(TransactionStatus status) {
                OwnerEntity owner = ownerRepository.findById(1L)
                        .orElseThrow(() -> new IllegalArgumentException("Owner not found"));

                Room room = Room.builder()
                        .roomNumber(2)
                        .roomSize(4)
                        .ownerEntity(owner)  // 영속화된 owner 엔티티 사용
                        .build();

                roomRepository.save(room);  // Room 엔티티 저장
                return null;
            }
        });
    }

}