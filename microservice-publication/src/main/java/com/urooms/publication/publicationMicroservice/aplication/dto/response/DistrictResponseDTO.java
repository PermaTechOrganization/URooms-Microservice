package com.urooms.publication.publicationMicroservice.aplication.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DistrictResponseDTO {

    private int id;

    private String name;

    private String coordinates;

}
