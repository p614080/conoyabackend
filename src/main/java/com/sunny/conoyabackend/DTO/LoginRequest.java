package com.sunny.conoyabackend.DTO;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginRequest {

    private String userEmail;
    private String userPassword;

    private String ownerEmail;
    private String ownerPassword;
}
