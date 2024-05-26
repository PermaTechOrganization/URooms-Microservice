package com.urooms.review.reviewMicroservice.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentRequestDTO {

    @NotBlank(message = "Comment is mandatory")
    private String comment;

    @NotBlank(message = "Rating is mandatory")
    private int rating;

    @NotBlank(message = "StudentId is mandatory")
    private int student;

    @NotBlank(message = "PublicationId is mandatory")
    private int publication;

}
