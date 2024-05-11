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
public class LocationRequestDTO {
    @NotBlank(message = "Address is mandatory")
    private String address;

    @NotBlank(message = "Detail is mandatory")
    private String detail;

    @NotBlank(message = "District is mandatory")
    private int district;

    @NotBlank(message = "Publication is mandatory")
    private int publication;
}
