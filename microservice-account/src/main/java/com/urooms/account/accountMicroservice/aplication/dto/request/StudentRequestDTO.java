package com.urooms.account.accountMicroservice.aplication.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentRequestDTO {

    @NotBlank(message = "gender is required")
    private String gender;

    @NotBlank(message = "DNI is required")
    private String dni;

    @NotBlank(message = "Phone is required")
    private String phone;

    @NotBlank(message = "Photo URL is required")
    private String photoUrl;

    @NotBlank(message = "Account is required")
    private String account;

    @NotBlank(message = "Career is required")
    private int career;

    @NotBlank(message = "University is required")
    private int university;

}
