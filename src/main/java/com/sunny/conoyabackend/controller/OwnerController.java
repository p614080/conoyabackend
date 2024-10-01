package com.sunny.conoyabackend.controller;

import com.sunny.conoyabackend.entity.Owner;
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
    public ResponseEntity<Owner> updateStoreInfo(@PathVariable Long id, @RequestBody Owner updatedOwner) {
        Owner owner = ownerService.updateStoreInfo(id, updatedOwner.getStoreName()
        ,updatedOwner.getDescription(), updatedOwner.getLocation(), updatedOwner.getImageUrl());
    return ResponseEntity.ok(owner);
    }
}
