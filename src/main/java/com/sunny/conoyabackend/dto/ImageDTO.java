package com.sunny.conoyabackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageDTO {

    private Long imageId; // 이미지 ID
    private String imageUrl; // 이미지 URL
    private String description; // 이미지 설명
}