package com.urooms.management.managementMicroservice.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RentalContractResponseDTO {

    private int id;

    private LocalDate startDate;

    private LocalDate endDate;

    private String signatureLessor;

    private String signatureStudent;

    private double price;

    private String conditions;

    private String description;

    private String contentUrl;

    private LessorResponseDTO lessor;

    private StudentResponseDTO student;

}
