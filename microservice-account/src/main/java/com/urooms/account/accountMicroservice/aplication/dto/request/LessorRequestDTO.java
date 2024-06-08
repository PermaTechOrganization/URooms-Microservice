package com.urooms.account.accountMicroservice.aplication.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LessorRequestDTO {

    @NotBlank(message = "dni is mandatory")
    private String dni;

    @NotBlank(message = "phone is mandatory")
    private String phone;

    @NotBlank(message = "photoUrl is mandatory")
    private String photoUrl;

    @NotBlank(message = "account is mandatory")
    private String account;

}
