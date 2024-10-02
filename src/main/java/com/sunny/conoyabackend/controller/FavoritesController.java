package com.sunny.conoyabackend.controller;

import com.sunny.conoyabackend.dto.UserDTO;
import com.sunny.conoyabackend.entity.UserEntity;
import com.sunny.conoyabackend.service.FavoritesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/favorites")
public class FavoritesController {
    private final FavoritesService favoritesService;


}
