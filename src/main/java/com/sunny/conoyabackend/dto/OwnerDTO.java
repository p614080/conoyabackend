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

    private String ownerId;
    private String ownerEmail;
    private String storeName;
    private String description;
    private String location;
    private String imageUrl;


    private String newPassword;
    private String oldPassword;
}
