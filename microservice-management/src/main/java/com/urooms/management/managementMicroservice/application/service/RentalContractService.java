package com.urooms.management.managementMicroservice.application.service;

import com.urooms.management.managementMicroservice.application.dto.request.RentalContractRequestDTO;
import com.urooms.management.managementMicroservice.application.dto.response.RentalContractResponseDTO;
import com.urooms.management.shared.model.dto.response.ApiResponse;

import java.util.List;

public interface RentalContractService {

    ApiResponse<RentalContractResponseDTO> getRentalContractById(int id);

    ApiResponse<Void> deleteRentalContract(int id);

    ApiResponse<RentalContractResponseDTO> createRentalContract(RentalContractRequestDTO rentalContractRequestDTO);

    ApiResponse<RentalContractResponseDTO> updateRentalContract(int id, RentalContractRequestDTO rentalContractRequestDTO);

    ApiResponse<List<RentalContractResponseDTO>> getRentalContractByLessor(int lessor);

    ApiResponse<List<RentalContractResponseDTO>> getRentalContractByStudent(int student);

}
