package com.urooms.account.accountMicroservice.aplication.services;

import com.urooms.account.accountMicroservice.aplication.dto.request.LessorRequestDTO;
import com.urooms.account.accountMicroservice.aplication.dto.response.LessorClientResponseDTO;
import com.urooms.account.accountMicroservice.aplication.dto.response.LessorResponseDTO;
import com.urooms.account.shared.model.dto.response.ApiResponse;

import java.util.List;

public interface LessorService {

    ApiResponse<List<LessorResponseDTO>> getLessors();

    ApiResponse<LessorResponseDTO> getLessorById(int id);

    ApiResponse<LessorClientResponseDTO> getLessorClientById(int id);

    ApiResponse<Void> deleteLessor(int id);

    ApiResponse<LessorResponseDTO> createLessor(LessorRequestDTO lessorRequestDTO);

    ApiResponse<LessorResponseDTO> updateLessor(int id, LessorRequestDTO lessorRequestDTO);

}
