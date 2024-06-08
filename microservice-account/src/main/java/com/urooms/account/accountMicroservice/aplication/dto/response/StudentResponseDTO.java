package com.urooms.account.accountMicroservice.aplication.dto.response;

import com.urooms.account.accountMicroservice.domain.entities.Career;
import com.urooms.account.accountMicroservice.domain.entities.University;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentResponseDTO {

    private int id;

    private String gender;

    private String dni;

    private String phone;

    private String photoUrl;

    private AccountResponseDTO account;

    private Career career;

    private University university;

}
