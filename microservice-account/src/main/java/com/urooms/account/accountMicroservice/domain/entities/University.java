package com.urooms.account.accountMicroservice.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "university")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", length = 150, nullable = false)
    private String name;

    @Column(name = "description", length = 500, nullable = false)
    private String description;

}
