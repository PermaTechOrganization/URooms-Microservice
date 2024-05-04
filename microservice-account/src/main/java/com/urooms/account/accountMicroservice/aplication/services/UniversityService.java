package com.urooms.account.accountMicroservice.aplication.services;

import com.urooms.account.accountMicroservice.aplication.dto.request.UniversityRequestDTO;
import com.urooms.account.accountMicroservice.aplication.dto.response.UniversityResponseDTO;
import com.urooms.account.accountMicroservice.domain.entities.University;
import com.urooms.account.shared.model.dto.response.ApiResponse;

import java.util.List;

public interface UniversityService {

    ApiResponse<UniversityResponseDTO> getUniversityById(int id);

    ApiResponse<List<UniversityResponseDTO>> getAllUniversities();

    ApiResponse<Void> deleteUniversity(int id);

    ApiResponse<UniversityResponseDTO> createUniversity(UniversityRequestDTO universityRequestDTO);

    ApiResponse<UniversityResponseDTO> updateUniversity(int id, UniversityRequestDTO universityRequestDTO);
}
