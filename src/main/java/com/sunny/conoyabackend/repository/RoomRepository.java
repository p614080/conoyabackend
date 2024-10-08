package com.sunny.conoyabackend.repository;

import com.sunny.conoyabackend.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room,Long> {
    List<Room> findAllByOwnerEntity_OwnerId(Long ownerId);
}
