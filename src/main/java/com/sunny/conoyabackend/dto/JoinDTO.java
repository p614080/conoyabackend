package com.sunny.conoyabackend.dto;



import com.sunny.conoyabackend.entity.OwnerEntity;
import com.sunny.conoyabackend.entity.UserEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JoinDTO {

    // 회원가입 요청처리
    @NotBlank(message = "(일반회원)로그인 이메일을 입력해주세요.")
    @Email
    private String userEmail;
    @NotBlank(message = "(점주회원)로그인 이메일을 입력해주세요.")
    @Email
    private String ownerEmail;

    // 비밀번호
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,25}",
            message = "비밀번호는 영문, 숫자, 특수기호가 적어도 1개 이상씩 포함된 8~25자의 비밀번호여야 합니다.")
    private String userPassword;
    private String userPasswordCheck;
    private String ownerPassword;
    private String ownerPasswordCheck;


    // 닉네임, 사업자 번호, 점포명
    @NotBlank(message = "닉네임을 입력해주세요.")
    private String userNickname;
    @NotBlank(message = "사업자 번호를 입력해주세요")
    private String ownerNum;
    @NotBlank(message = "가게명이 비어있습니다.")
    private String storeName;



    // DTO -> Entity로 전송
    public UserEntity userEntity() {
        return UserEntity.builder()
                .userEmail(this.userEmail)
                .userPassword(this.userPassword)
                .userNickname(this.userNickname)
                .build();

    }

    public OwnerEntity ownerEntity() {
        return OwnerEntity.builder()
                .ownerEmail(this.ownerEmail)
                .ownerNum(this.ownerNum)
                .ownerPassword(this.ownerPassword)
                .storeName(this.storeName)
                .build();
    }

}
