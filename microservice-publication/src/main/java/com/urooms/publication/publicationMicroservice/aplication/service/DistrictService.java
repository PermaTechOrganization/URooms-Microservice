package com.urooms.publication.publicationMicroservice.aplication.service;

import com.urooms.publication.publicationMicroservice.aplication.dto.request.DistrictRequestDTO;
import com.urooms.publication.publicationMicroservice.aplication.dto.response.DistrictResponseDTO;
import com.urooms.publication.shared.model.dto.response.ApiResponse;

import java.util.List;

public interface DistrictService {

    ApiResponse<DistrictResponseDTO> getDistrictById(int id);

    ApiResponse<DistrictResponseDTO> createDistrict(DistrictRequestDTO districtRequestDTO);

    ApiResponse<Void> deleteDistrict(int id);

    ApiResponse<DistrictResponseDTO> updateDistrict(int id, DistrictRequestDTO districtRequestDTO);

    ApiResponse<List<DistrictResponseDTO>> getDistricts();
}
