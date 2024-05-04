package com.urooms.account.accountMicroservice.aplication.services;

import com.urooms.account.accountMicroservice.aplication.dto.request.CareerRequestDTO;
import com.urooms.account.accountMicroservice.aplication.dto.response.CareerResponseDTO;
import com.urooms.account.shared.model.dto.response.ApiResponse;

import java.util.List;

public interface CareerService {

    ApiResponse<CareerResponseDTO> getCareerById(int id);

    ApiResponse<List<CareerResponseDTO>> getAllCareers();

    ApiResponse<CareerResponseDTO> createCareer(CareerRequestDTO careerRequestDto);

    ApiResponse<CareerResponseDTO> updateCareer(int id, CareerRequestDTO careerRequestDto);

    ApiResponse<Void> deleteCareer(int id);
}
