package com.sunny.conoyabackend.domain;

import com.sunny.conoyabackend.entity.OwnerEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Embeddable
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_review_comment")
public class ReviewComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentNo;
    @Column(nullable = false)
    private String comment;

    @OneToOne
    @JoinColumn(name = "review_no", nullable = false)
    private Review review;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ownerId", nullable = false)
    private OwnerEntity ownerEntity;

    @Column(nullable = false)
    private LocalDateTime commentDate;

    public void setReview(Review review) {
        this.review = review;
    }

}
