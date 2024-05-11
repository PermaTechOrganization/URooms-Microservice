package com.urooms.publication.publicationMicroservice.aplication.service;

import com.urooms.publication.publicationMicroservice.aplication.dto.request.TypePropertyRequestDTO;
import com.urooms.publication.publicationMicroservice.aplication.dto.response.TypePropertyResponseDTO;
import com.urooms.publication.shared.model.dto.response.ApiResponse;

import java.util.List;

public interface TypePropertyService {

    ApiResponse<TypePropertyResponseDTO> getTypePropertyById(int id);

    ApiResponse<TypePropertyResponseDTO> createTypeProperty(TypePropertyRequestDTO typePropertyRequestDTO);

    ApiResponse<TypePropertyResponseDTO> updateTypeProperty(int id, TypePropertyRequestDTO typePropertyRequestDTO);

    ApiResponse<Void> deleteTypeProperty(int id);

    ApiResponse<List<TypePropertyResponseDTO>> getTypeProperties();
}
