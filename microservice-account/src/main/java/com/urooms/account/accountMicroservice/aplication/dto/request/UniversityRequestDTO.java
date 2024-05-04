package com.urooms.account.accountMicroservice.aplication.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UniversityRequestDTO {

    @NotBlank(message = "name is mandatory")
    private String name;

    @NotBlank(message = "description is mandatory")
    private String description;


}
