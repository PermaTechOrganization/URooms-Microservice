package com.urooms.publication.publicationMicroservice.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.urooms.publication.publicationMicroservice.aplication.dto.response.LessorResponseDTO;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "publication")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title", length = 50, nullable = false)
    private String title;

    @Column(name = "description", length = 150, nullable = false)
    private String description;

    @Column(name = "imageURL", length = 150, nullable = false)
    private String imageURL;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "rating", nullable = false)
    private double rating;

    /*
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lessor_id", nullable = false)*/

    //@Column(name = "lessor_id", nullable = false)
    private int lessorId;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_property_id", nullable = false)
    private TypeProperty typeProperty;
}
