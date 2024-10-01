package com.sunny.conoyabackend.domain;

import jakarta.persistence.*;
import lombok.*;

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

    private String noraebang;
    private String writer;
    private byte rating;
    private String review_content;
    private String dueDate;
}
