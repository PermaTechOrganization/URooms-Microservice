package com.urooms.account.accountMicroservice.aplication.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UniversityResponseDTO {

    private int id;

    private String name;

    private String description;

}
