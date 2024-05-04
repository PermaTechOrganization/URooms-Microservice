package com.urooms.account.accountMicroservice.aplication.dto.request;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountRequestDTO {

    @NotBlank(message = "userName is mandatory")
    private String userName;

    @NotBlank(message = "email is mandatory")
    private String email;

    @NotBlank(message = "password is mandatory")
    private String password;

    @NotBlank(message = "role is mandatory")
    private int role;
}
