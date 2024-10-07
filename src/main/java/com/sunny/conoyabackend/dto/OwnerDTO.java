package com.sunny.conoyabackend.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OwnerDTO {

    private Long ownerId;
    private String ownerEmail;
    private String storeName;
    private String description;
    private String location;
    private String imageUrl;


    private String newPassword;
    private String oldPassword;

    public OwnerDTO(String storeName, String location) {
        this.storeName = storeName;
        this.location = location;
    }
}
