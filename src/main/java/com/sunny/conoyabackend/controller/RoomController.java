package com.sunny.conoyabackend.controller;

import com.sunny.conoyabackend.dto.RoomDTO;
import com.sunny.conoyabackend.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/room")
public class RoomController {
    private final RoomService roomService;

    @GetMapping("noraebang/{ownerId}/list")
    public ResponseEntity<List<RoomDTO>> getRoomsByOwnerId(@PathVariable Long ownerId){
        List<RoomDTO> roomDTOList = roomService.getRoomsByOwnerId(ownerId);
        return ResponseEntity.ok(roomDTOList);
    }

    @DeleteMapping
    public Map<String, String> remove(@PathVariable(name = "roomId") Long roomId){
        roomService.remove(roomId);
        return Map.of("result", "SUCCESS");
    }
}
