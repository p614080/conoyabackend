package com.sunny.conoyabackend.dto;

import com.sunny.conoyabackend.entity.OwnerEntity;
import com.sunny.conoyabackend.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FavoritesDTO {
    private Long favorites_id;
    private UserEntity userEntity;
    private OwnerEntity ownerEntity;

    public FavoritesDTO(OwnerEntity ownerEntity) {
        this.ownerEntity = ownerEntity;
    }

}
