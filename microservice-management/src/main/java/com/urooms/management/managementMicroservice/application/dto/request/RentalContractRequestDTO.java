package com.urooms.management.managementMicroservice.application.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RentalContractRequestDTO {

    @NotBlank(message = "startDate is mandatory")
    private LocalDate startDate;

    @NotBlank(message = "endDate is mandatory")
    private LocalDate endDate;

    @NotBlank(message = "signatureLessor is mandatory")
    private String signatureLessor;

    @NotBlank(message = "signatureStudent is mandatory")
    private String signatureStudent;

    @NotBlank(message = "price is mandatory")
    private double price;

    @NotBlank(message = "conditions is mandatory")
    private String conditions;

    @NotBlank(message = "description is mandatory")
    private String description;

    @NotBlank(message = "contentUrl is mandatory")
    private String contentUrl;

    @Column(name = "lessorId", nullable = false)
    private int lessor;

    @Column(name = "studentId", nullable = false)
    private int student;

}
