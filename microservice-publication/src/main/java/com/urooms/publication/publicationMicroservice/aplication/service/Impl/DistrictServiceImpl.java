package com.urooms.publication.publicationMicroservice.aplication.service.Impl;

import com.urooms.publication.publicationMicroservice.aplication.dto.request.DistrictRequestDTO;
import com.urooms.publication.publicationMicroservice.aplication.dto.response.DistrictResponseDTO;
import com.urooms.publication.publicationMicroservice.aplication.service.DistrictService;
import com.urooms.publication.publicationMicroservice.domain.entities.District;
import com.urooms.publication.publicationMicroservice.infraestructure.repositories.DistrictRepository;
import com.urooms.publication.shared.model.dto.response.ApiResponse;
import com.urooms.publication.shared.model.enums.Estatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepository districtRepository;

    private final ModelMapper modelMapper;

    public DistrictServiceImpl(DistrictRepository districtRepository, ModelMapper modelMapper) {
        this.districtRepository = districtRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiResponse<DistrictResponseDTO> getDistrictById(int id) {
        Optional<District> districtOptional = districtRepository.findById(id);
        if (districtOptional.isPresent()) {
            District district = districtOptional.get();
            DistrictResponseDTO responseDTO = modelMapper.map(district, DistrictResponseDTO.class);
            return new ApiResponse<>("District fetched successfully", Estatus.SUCCESS, responseDTO);
        } else {
            return new ApiResponse<>("District not found", Estatus.ERROR, null);
        }
    }

    @Override
    public ApiResponse<DistrictResponseDTO> createDistrict(DistrictRequestDTO districtRequestDTO) {
        var district = modelMapper.map(districtRequestDTO, District.class);
        districtRepository.save(district);
        var response = modelMapper.map(district, DistrictResponseDTO.class);
        return new ApiResponse<>("District created successfully", Estatus.SUCCESS, response);
    }

    @Override
    public ApiResponse<Void> deleteDistrict(int id) {
        Optional<District> districtOptional = districtRepository.findById(id);
        if (districtOptional.isPresent()) {
            districtRepository.deleteById(id);
            return new ApiResponse<>("District deleted successfully", Estatus.SUCCESS, null);
        } else {
            return new ApiResponse<>("District not found", Estatus.ERROR, null);
        }
    }

    @Override
    public ApiResponse<DistrictResponseDTO> updateDistrict(int id, DistrictRequestDTO districtRequestDTO) {
        Optional<District> districtOptional = districtRepository.findById(id);

        if (districtOptional.isEmpty()) {
            return new ApiResponse<>("District not found", Estatus.ERROR, null);
        } else {
            District district = districtOptional.get();
            modelMapper.map(districtRequestDTO, district);
            districtRepository.save(district);
            DistrictResponseDTO response = modelMapper.map(district, DistrictResponseDTO.class);
            return new ApiResponse<>("District updated successfully", Estatus.SUCCESS, response);
        }
    }

    @Override
    public ApiResponse<List<DistrictResponseDTO>> getDistricts() {
        List<District> districtList = (List<District>) districtRepository.findAll();
        List<DistrictResponseDTO> districtDTOList = districtList.stream()
                .map(entity -> modelMapper.map(entity, DistrictResponseDTO.class))
                .collect(Collectors.toList());

        return new ApiResponse<>("All districts fetched successfully", Estatus.SUCCESS, districtDTOList);
    }

}
