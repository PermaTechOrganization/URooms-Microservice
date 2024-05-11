package com.urooms.publication.publicationMicroservice.aplication.service;

import com.urooms.publication.publicationMicroservice.aplication.dto.request.PublicationRequestDTO;
import com.urooms.publication.publicationMicroservice.aplication.dto.response.LessorResponseDTO;
import com.urooms.publication.publicationMicroservice.aplication.dto.response.PublicationResponseDTO;
import com.urooms.publication.shared.model.dto.response.ApiResponse;

import java.util.List;

public interface PublicationService {

    ApiResponse<List<PublicationResponseDTO>> getPublications();

    ApiResponse<List<PublicationResponseDTO>> getPublicationByLessorId(int lessorId);

    ApiResponse<List<PublicationResponseDTO>> getPublicationByTypePropertyId(int typePropertyId);

    ApiResponse<PublicationResponseDTO> getPublicationById(int id);

    ApiResponse<PublicationResponseDTO> createPublication(PublicationRequestDTO publicationRequestDTO);

    ApiResponse<PublicationResponseDTO> updatePublication(int id, PublicationRequestDTO publicationRequestDTO);

    ApiResponse<LessorResponseDTO> getLessorById(int id);

    ApiResponse<Void> deletePublication(int id);

}
