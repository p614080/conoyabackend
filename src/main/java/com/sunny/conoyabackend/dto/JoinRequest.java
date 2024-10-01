package com.sunny.conoyabackend.dto;


import com.sunny.conoyabackend.Role;
import com.sunny.conoyabackend.entity.Owner;
import com.sunny.conoyabackend.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JoinRequest {

    // 회원가입 요청처리
    @NotBlank(message = "로그인 아이디를 입력해주세요.")
    private String userEmail;
    @NotBlank(message = "사업자번호를 입력해주세요.")
    private String ownerNum;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String userPassword;
    private String userPasswordCheck;
    private String ownerPassword;
    private String ownerPasswordCheck;


    @NotBlank(message = "닉네임을 입력해주세요.")
    private String userNickname;

    @NotBlank(message = "가게명이 비어있습니다.")
    private String storeName;


    //비밀번호 암호화x
    public User userEntity() {
        return User.builder()
                .userEmail(this.userEmail)
                .userPassword(this.userPassword)
                .userNickname(this.userNickname)
                .role(Role.USER)
                .build();
    }

    public Owner ownerEntity() {
        return Owner.builder()
                .ownerNum(this.ownerNum)
                .ownerPassword(this.ownerPassword)
                .storeName(this.storeName)
                .role(Role.OWNER)
                .build();
    }

}
