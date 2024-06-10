package com.urooms.publication.publicationMicroservice.aplication.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PublicationRequestDTO {
    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotBlank(message = "Description is mandatory")
    private String description;

    @NotBlank(message = "ImageURL is mandatory")
    private String imageURL;

    @NotBlank(message = "Price is mandatory")
    private double price;

    @NotBlank(message = "Rating is mandatory")
    private double rating;

    @NotBlank(message = "Lessor is mandatory")
    private int lessor;

    @NotBlank(message = "TypeProperty is mandatory")
    private int typeProperty;
}
