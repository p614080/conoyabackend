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
    @NotBlank(message = "로그인 아이디가 비어있습니다.")
    private  String ownerEmail;
    private String userEmail;

    @NotBlank(message = "비밀번호가 비어있습니다.")
    private String userPassword;
    private String userPasswordCheck;
    private String ownerPassword;
    private String ownerPasswordCheck;


    @NotBlank(message = "닉네임이 비어있습니다.")
    private String userNickname;
    private String ownerNickname;


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
                .ownerEmail(this.ownerEmail)
                .ownerPassword(this.ownerPassword)
                .ownerNickname(this.ownerNickname)
                .role(Role.OWNER)
                .build();
    }

}
