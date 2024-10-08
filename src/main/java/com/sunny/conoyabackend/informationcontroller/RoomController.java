//package com.sunny.conoyabackend.informationcontroller;
//
//import com.sunny.conoyabackend.entity.Room;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/rooms")
//public class RoomController {
//
//    private final List<Room> rooms = new ArrayList<>();
//
//    public RoomController() {
//        // 방 데이터 초기화
//        rooms.add(new Room(1, true, 60, 10));
//        rooms.add(new Room(2, false, 0, 0));
//        rooms.add(new Room(3, true, 30, 5));
//    }
//
//    @GetMapping
//    public List<Room> getAllRooms() {
//        return rooms;
//    }
//
//    @GetMapping("/{roomNumber}")
//    public Room getRoom(@PathVariable int roomNumber) {
//        return rooms.stream()
//                .filter(room -> room.getRoomNumber() == roomNumber)
//                .findFirst()
//                .orElse(null);
//    }
//
//    @PostMapping
//    public Room addRoom(@RequestBody Room room) {
//        rooms.add(room);
//        return room;
//    }
//
//    @PutMapping("/{roomNumber}")
//    public Room updateRoom(@PathVariable int roomNumber, @RequestBody Room updatedRoom) {
//        for (int i = 0; i < rooms.size(); i++) {
//            if (rooms.get(i).getRoomNumber() == roomNumber) {
//                rooms.set(i, updatedRoom);
//                return updatedRoom;
//            }
//        }
//        return null;
//    }
//
//    @DeleteMapping("/{roomNumber}")
//    public String deleteRoom(@PathVariable int roomNumber) {
//        rooms.removeIf(room -> room.getRoomNumber() == roomNumber);
//        return "Room " + roomNumber + " deleted.";
//    }
//}
