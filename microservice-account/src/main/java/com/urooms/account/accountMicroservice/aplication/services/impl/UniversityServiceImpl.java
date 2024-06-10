package com.urooms.account.accountMicroservice.aplication.services.impl;

import com.urooms.account.accountMicroservice.aplication.dto.request.UniversityRequestDTO;
import com.urooms.account.accountMicroservice.aplication.dto.response.CareerResponseDTO;
import com.urooms.account.accountMicroservice.aplication.dto.response.UniversityResponseDTO;
import com.urooms.account.accountMicroservice.aplication.services.UniversityService;
import com.urooms.account.accountMicroservice.domain.entities.Career;
import com.urooms.account.accountMicroservice.domain.entities.University;
import com.urooms.account.accountMicroservice.infraestructure.repositories.UniversityRepository;
import com.urooms.account.shared.model.dto.response.ApiResponse;
import com.urooms.account.shared.model.enums.Estatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UniversityServiceImpl implements UniversityService {

    private final UniversityRepository universityRepository;
    private final ModelMapper modelMapper;

    public UniversityServiceImpl(UniversityRepository universityRepository, ModelMapper modelMapper) {
        this.universityRepository = universityRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiResponse<UniversityResponseDTO> getUniversityById(int id) {
        Optional<University> universityOptional = universityRepository.findById(id);
        if (universityOptional.isPresent()) {
            University university = universityOptional.get();
            UniversityResponseDTO responseDTO = modelMapper.map(university, UniversityResponseDTO.class);
            return new ApiResponse<>("University fetched successfully", Estatus.SUCCESS, responseDTO);
        } else {
            return new ApiResponse<>("University not found", Estatus.ERROR, null);
        }
    }

    @Override
    public ApiResponse<List<UniversityResponseDTO>> getAllUniversities() {
        List<University> universityList = (List<University>) universityRepository.findAll();
        List<UniversityResponseDTO> universityDTOList = universityList.stream()
                .map(entity -> modelMapper.map(entity, UniversityResponseDTO.class))
                .collect(Collectors.toList());

        return new ApiResponse<>("All universities fetched successfully", Estatus.SUCCESS, universityDTOList);
    }

    @Override
    public ApiResponse<UniversityResponseDTO> createUniversity(UniversityRequestDTO universityRequestDTO) {
        var university = modelMapper.map(universityRequestDTO, University.class);
        universityRepository.save(university);
        var response = modelMapper.map(university, UniversityResponseDTO.class);
        return new ApiResponse<>("University created successfully", Estatus.SUCCESS, response);
    }

    @Override
    public ApiResponse<UniversityResponseDTO> updateUniversity(int id, UniversityRequestDTO universityRequestDTO) {
        Optional<University> universityOptional = universityRepository.findById(id);
        if (universityOptional.isEmpty()) {
            return new ApiResponse<>("University not found", Estatus.ERROR, null);
        } else {
            University university = universityOptional.get();
            modelMapper.map(universityRequestDTO, university);
            universityRepository.save(university);
            UniversityResponseDTO response = modelMapper.map(university, UniversityResponseDTO.class);
            return new ApiResponse<>("University updated successfully", Estatus.SUCCESS, response);
        }
    }

    @Override
    public ApiResponse<Void> deleteUniversity(int id) {
        Optional<University> universityOptional = universityRepository.findById(id);
        if (universityOptional.isEmpty()) {
            return new ApiResponse<>("University not found", Estatus.ERROR, null);
        } else {
            universityRepository.deleteById(id);
            return new ApiResponse<>("University deleted successfully", Estatus.SUCCESS, null);
        }
    }

}
