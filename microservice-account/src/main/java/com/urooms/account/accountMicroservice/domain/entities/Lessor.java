package com.urooms.account.accountMicroservice.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "lessor")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Lessor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "dni", length = 8, nullable = false)
    private String dni;

    @Column(name = "phone", length = 9, nullable = false)
    private String phone;

    @Column(name = "photoUrl", length = 150, nullable = false)
    private String photoUrl;

    @Column(name = "account_id", nullable = false)
    private String account;

/*
    @Column(name = "firstName", length = 150, nullable = false)
    private String firstName;

    @Column(name = "lastName", length = 150, nullable = false)
    private String lastName;

    @Setter
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;*/

}
