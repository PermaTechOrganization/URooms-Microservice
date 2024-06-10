package com.urooms.review.reviewMicroservice.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "comments")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "comment", length = 500, nullable = false)
    private String comment;

    @Column(name = "rating", nullable = false)
    private int rating;

    @Column(name = "studentId", nullable = false)
    private int student;

    @Column(name = "publicationId", nullable = false)
    private int publication;

}
