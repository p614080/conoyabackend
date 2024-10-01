package com.sunny.conoyabackend.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tbl_review")
@Builder
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long review_no;

    private Long noraebang_no;
    private String writer;
    private short rating;
    private String review_content;
    private LocalDate dueDate;
}
