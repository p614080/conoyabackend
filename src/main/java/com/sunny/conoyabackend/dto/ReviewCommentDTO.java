package com.sunny.conoyabackend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewCommentDTO {
    private Long commentNo; // 댓글 ID
    private String comment; // 댓글 내용
    private Long reviewNo; // 연결된 리뷰 ID
    private Long ownerId; // 댓글 작성자의 사장님 ID

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss") // 날짜 형식 지정
    private LocalDateTime commentDate; // 댓글 작성일시
}