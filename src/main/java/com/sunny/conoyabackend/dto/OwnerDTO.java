package com.sunny.conoyabackend.dto;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OwnerDTO {

    private Long ownerId;
    private String ownerEmail;
    private String ownerNum;
    private String storeName;
    private String location;
    private String imageUrl;
    private String ownerPassword;
    private String newPassword;

}
