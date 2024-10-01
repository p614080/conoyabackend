package com.sunny.conoyabackend.dto;


import com.sunny.conoyabackend.entity.entity.User;
import com.sunny.conoyabackend.UserRole;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JoinRequest {
    @NotBlank(message = "로그인 아이디가 비어있습니다.")
    private  String loginEmail;

    @NotBlank(message = "비밀번호가 비어있습니다.")
    private String password;
    private String passwordCheck;

    @NotBlank(message = "닉네임이 비어있습니다.")
    private String nickname;

    //비밀번호 암호화x
    public User toEntity() {
        return User.builder()
                .loginEmail(this.loginEmail)
                .password(this.password)
                .nickname(this.nickname)
                .role(UserRole.USER)
                .build();
    }

}
