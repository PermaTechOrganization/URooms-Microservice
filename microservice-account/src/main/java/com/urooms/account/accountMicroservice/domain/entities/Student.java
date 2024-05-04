package com.urooms.account.accountMicroservice.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "student")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "firstName", length = 150, nullable = false)
    private String firstName;

    @Column(name = "lastName", length = 150, nullable = false)
    private String lastName;

    @Column(name = "gender", length = 15,nullable = false)
    private String gender;

    @Column(name = "dni", length = 8, nullable = false)
    private String dni;

    @Column(name = "phone", length = 9, nullable = false)
    private String phone;

    @Column(name = "photoUrl", length = 150, nullable = false)
    private String photoUrl;

    @Setter
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "career_id", nullable = false)
    private Career career;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id", nullable = false)
    private University university;

}
