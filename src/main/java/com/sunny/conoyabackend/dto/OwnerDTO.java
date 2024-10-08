package com.sunny.conoyabackend.dto;


import lombok.Builder;
import lombok.Getter;

import lombok.Setter;

@Getter
@Setter
@Builder
public class OwnerDTO {

    private Long ownerId;
    private String ownerEmail;
    private String storeName;
    private String description;
    private String location;
    private String imageUrl;


    private String newPassword;
    private String oldPassword;

}
