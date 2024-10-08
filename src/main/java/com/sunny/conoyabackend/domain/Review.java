package com.sunny.conoyabackend.domain;

import com.sunny.conoyabackend.entity.OwnerEntity;
import com.sunny.conoyabackend.entity.UserEntity;
import com.sunny.conoyabackend.infomation.Room;
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
    private Long reviewNo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ownerId", nullable = false)
    private OwnerEntity ownerEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", nullable = false)
    private UserEntity userEntity;

    private short rating;
    private String reviewContent;
    private LocalDate dueDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "commentNo")
    private ReviewComment reviewComment;
}
