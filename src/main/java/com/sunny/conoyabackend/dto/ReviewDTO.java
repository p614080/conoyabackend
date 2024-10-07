package com.sunny.conoyabackend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sunny.conoyabackend.entity.OwnerEntity;
import com.sunny.conoyabackend.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
    private Long review_no;

    private Long ownerId;
    private Long userId;
    private short rating;
    private String review_content;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dueDate;
}
