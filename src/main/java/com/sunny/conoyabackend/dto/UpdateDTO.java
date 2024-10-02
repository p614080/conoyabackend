package com.sunny.conoyabackend.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDTO {

    private String newNickname;
    private String newPassword;
    private String oldPassword;
}
