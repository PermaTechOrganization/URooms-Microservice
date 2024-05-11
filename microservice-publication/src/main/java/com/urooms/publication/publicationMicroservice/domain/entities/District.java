package com.urooms.publication.publicationMicroservice.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "district")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "coordinates", length = 150, nullable = false)
    private String coordinates;
}