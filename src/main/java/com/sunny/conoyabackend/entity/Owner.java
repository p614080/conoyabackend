package com.sunny.conoyabackend.entity;


import com.sunny.conoyabackend.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ownerId;

    private String ownerNum;
    private String ownerPassword;
    private String storeName;

    private Role role;


    // Nickname에 대한 setter만 추가
    public void setOwnerPassword(String ownerPassword) {
        this.ownerPassword = ownerPassword;
    }
}
