package com.urooms.publication.publicationMicroservice.aplication.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PublicationClientResponseDTO {
    private int id;

    private String title;

    private String description;

    private String imageURL;

    private double price;

    private double rating;

}
