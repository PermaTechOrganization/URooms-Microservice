package com.urooms.publication.publicationMicroservice.aplication.dto.response;

import com.urooms.publication.publicationMicroservice.domain.entities.District;
import com.urooms.publication.publicationMicroservice.domain.entities.Publication;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocationResponseDTO {

    private int id;

    private String address;

    private String detail;

    private District district;

    private Publication publication;
}
