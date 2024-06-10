package com.urooms.publication.publicationMicroservice.aplication.dto.response;

import com.urooms.publication.publicationMicroservice.domain.entities.TypeProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PublicationResponseDTO {
    private int id;

    private String title;

    private String description;

    private String imageURL;

    private double price;

    private double rating;

    private LessorResponseDTO lessor;

    private TypeProperty typeProperty;
}
