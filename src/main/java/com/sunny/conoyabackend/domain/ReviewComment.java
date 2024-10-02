package com.sunny.conoyabackend.domain;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.time.LocalDate;

@Embeddable
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewComment {
    private String comment;
    private String comment_writer;
    private LocalDate commentDate;
}
