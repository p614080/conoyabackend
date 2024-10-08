package com.sunny.conoyabackend.controller;

import com.sunny.conoyabackend.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/room")
public class RoomController {
    private final RoomService roomService;

}
