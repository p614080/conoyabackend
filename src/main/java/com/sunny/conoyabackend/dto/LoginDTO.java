package com.sunny.conoyabackend.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginDTO {
    // 로그인 요청 처리
    private String userEmail;
    private String userPassword;



    private String ownerEmail;
    private String ownerPassword;
}
