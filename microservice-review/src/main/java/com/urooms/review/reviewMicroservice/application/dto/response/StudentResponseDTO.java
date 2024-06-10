package com.urooms.review.reviewMicroservice.application.dto.response;

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

    private String firstName;

    private String lastName;

    private String gender;

    private String dni;

    private String phone;

    private String photoUrl;

}
