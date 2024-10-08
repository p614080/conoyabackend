package com.sunny.conoyabackend.repository;

import com.sunny.conoyabackend.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room,Long> {
}
