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
public class TypePropertyRequestDTO {
    @NotBlank(message = "Type is mandatory")
    private String type;

    @NotBlank(message = "Description is mandatory")
    private String description;
}
