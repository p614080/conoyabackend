package com.sunny.conoyabackend.dto;


import jakarta.persistence.Lob;
import jakarta.validation.Valid;
import lombok.*;

@Valid
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
    private String description;

}
