package com.urooms.account.accountMicroservice.aplication.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LessorClientResponseDTO {

    private int id;

    private String firstName;

    private String lastName;

    private String dni;

    private String phone;

    private String photoUrl;

}
