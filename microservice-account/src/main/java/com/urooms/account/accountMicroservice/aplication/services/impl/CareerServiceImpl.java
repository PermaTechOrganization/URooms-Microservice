package com.urooms.account.accountMicroservice.aplication.services.impl;

import com.urooms.account.accountMicroservice.aplication.dto.request.CareerRequestDTO;
import com.urooms.account.accountMicroservice.aplication.dto.response.CareerResponseDTO;
import com.urooms.account.accountMicroservice.aplication.services.CareerService;
import com.urooms.account.accountMicroservice.domain.entities.Career;
import com.urooms.account.accountMicroservice.infraestructure.repositories.CareerRepository;
import com.urooms.account.shared.model.dto.response.ApiResponse;
import com.urooms.account.shared.model.enums.Estatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CareerServiceImpl implements CareerService {

    private final CareerRepository careerRepository;
    private final ModelMapper modelMapper;

    public CareerServiceImpl(CareerRepository careerRepository, ModelMapper modelMapper) {
        this.careerRepository = careerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiResponse<CareerResponseDTO> getCareerById(int id) {
        Optional<Career> careerOptional = careerRepository.findById(id);
        if (careerOptional.isPresent()) {
            Career career = careerOptional.get();
            CareerResponseDTO responseDTO = modelMapper.map(career, CareerResponseDTO.class);
            return new ApiResponse<>("Career fetched successfully", Estatus.SUCCESS, responseDTO);
        } else {
            return new ApiResponse<>("Career not found", Estatus.ERROR, null);
        }
    }

    @Override
    public ApiResponse<List<CareerResponseDTO>> getAllCareers() {
        List<Career> careerList = (List<Career>) careerRepository.findAll();
        List<CareerResponseDTO> careerDTOList = careerList.stream()
                .map(entity -> modelMapper.map(entity, CareerResponseDTO.class))
                .collect(Collectors.toList());

        return new ApiResponse<>("All careers fetched successfully", Estatus.SUCCESS, careerDTOList);
    }

    @Override
    public ApiResponse<CareerResponseDTO> createCareer(CareerRequestDTO careerRequestDto) {
        var career = modelMapper.map(careerRequestDto, Career.class);
        careerRepository.save(career);
        var response = modelMapper.map(career, CareerResponseDTO.class);
        return new ApiResponse<>("Career created successfully", Estatus.SUCCESS, response);
    }

    @Override
    public ApiResponse<CareerResponseDTO> updateCareer(int id, CareerRequestDTO careerRequestDto) {
        Optional<Career> careerOptional = careerRepository.findById(id);
        if (careerOptional.isEmpty()) {
            return new ApiResponse<>("Career not found", Estatus.ERROR, null);
        } else {
            Career career = careerOptional.get();
            modelMapper.map(careerRequestDto, career);
            careerRepository.save(career);
            CareerResponseDTO response = modelMapper.map(career, CareerResponseDTO.class);
            return new ApiResponse<>("Career updated successfully", Estatus.SUCCESS, response);
        }
    }

    @Override
    public ApiResponse<Void> deleteCareer(int id) {
        Optional<Career> careerOptional = careerRepository.findById(id);
        if (careerOptional.isEmpty()) {
            return new ApiResponse<>("Career not found", Estatus.ERROR, null);
        } else {
            careerRepository.deleteById(id);
            return new ApiResponse<>("Career deleted successfully", Estatus.SUCCESS, null);
        }
    }

}
