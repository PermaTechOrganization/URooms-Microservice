package com.urooms.management.managementMicroservice.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "rentalContract")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RentalContract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "startDate", nullable = false)
    private LocalDate startDate;

    @Column(name = "endDate", nullable = false)
    private LocalDate endDate;

    @Column(name = "signatureLessor", length = 100, nullable = false)
    private String signatureLessor;

    @Column(name = "signatureStudent", length = 100, nullable = false)
    private String signatureStudent;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "conditions", length = 500, nullable = false)
    private String conditions;

    @Column(name = "description", length = 500, nullable = false)
    private String description;

    @Column(name = "contentUrl", length = 100, nullable = false)
    private String contentUrl;

    @Column(name = "lessorId", nullable = false)
    private int lessor;

    @Column(name = "studentId", nullable = false)
    private int student;

}
