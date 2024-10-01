package com.sunny.conoyabackend.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginRequest {
    // 로그인 요청 처리
    private String userEmail;
    private String userPassword;

    private String ownerNum;
    private String ownerPassword;
}
