package com.urooms.publication.publicationMicroservice.aplication.service;

import com.urooms.publication.publicationMicroservice.aplication.dto.request.LocationRequestDTO;
import com.urooms.publication.publicationMicroservice.aplication.dto.response.LocationResponseDTO;
import com.urooms.publication.shared.model.dto.response.ApiResponse;

public interface LocationService {

    ApiResponse<LocationResponseDTO> getLocationById(int id);

    ApiResponse<LocationResponseDTO> createLocation(LocationRequestDTO locationRequestDTO);

    ApiResponse<LocationResponseDTO> updateLocation(int id, LocationRequestDTO locationRequestDTO);

    ApiResponse<Void> deleteLocation(int id);
}
