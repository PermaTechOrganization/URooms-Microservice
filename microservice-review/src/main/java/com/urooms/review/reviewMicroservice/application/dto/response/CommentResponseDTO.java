package com.urooms.review.reviewMicroservice.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentResponseDTO {

    private int id;

    private String comment;

    private int rating;

    private StudentResponseDTO student;

    private PublicationResponseDTO publication;

}
