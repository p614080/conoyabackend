package com.sunny.conoyabackend.domain;

import com.sunny.conoyabackend.entity.OwnerEntity;
import com.sunny.conoyabackend.entity.UserEntity;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ownerId", nullable = false)
    private OwnerEntity ownerEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private UserEntity userEntity;
    private short rating;
    private String review_content;
    private LocalDate dueDate;

    @OneToOne(mappedBy = "review", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ReviewComment reviewComment;
}
