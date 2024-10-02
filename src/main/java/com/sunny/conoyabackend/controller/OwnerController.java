package com.sunny.conoyabackend.controller;

import com.sunny.conoyabackend.dto.OwnerDTO;
import com.sunny.conoyabackend.entity.OwnerEntity;
import com.sunny.conoyabackend.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owners")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    // 노래방 정보 변경
    @PutMapping("/{id}/store-info")
    public ResponseEntity<OwnerEntity> updateStoreInfo(
            @PathVariable Long id,
            @RequestBody OwnerDTO ownerDTO) {
        OwnerEntity updatedOwner = ownerService.updateStoreInfo(id, ownerDTO);
        return ResponseEntity.ok(updatedOwner);
    }
}
