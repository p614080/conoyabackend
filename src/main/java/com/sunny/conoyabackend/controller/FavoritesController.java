package com.sunny.conoyabackend.controller;

import com.sunny.conoyabackend.dto.FavoritesDTO;
import com.sunny.conoyabackend.service.FavoritesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/favorites")
public class FavoritesController {
    private final FavoritesService favoritesService;

    // 즐겨찾기 추가
    @PostMapping("/add/{userId}/{ownerId}")
    public ResponseEntity<String> addFavorite(@PathVariable Long userId, @PathVariable Long ownerId) {
        favoritesService.addFavorites(userId, ownerId);
        return ResponseEntity.ok("즐겨찾기에 추가되었습니다.");
    }

    // 즐겨찾기 삭제
    @DeleteMapping("/remove/{userId}/{ownerId}")
    public ResponseEntity<String> removeFavorite(@PathVariable Long userId, @PathVariable Long ownerId) {
        favoritesService.removeFavorites(userId, ownerId);
        return ResponseEntity.ok("즐겨찾기에서 삭제되었습니다.");
    }

    // 특정 사용자의 즐겨찾기 조회
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<FavoritesDTO>> getFavoritesByUser(@PathVariable Long userId) {
        List<FavoritesDTO> favorites = favoritesService.getFavoritesByUserEntity(userId);
        return ResponseEntity.ok(favorites);
    }
}
