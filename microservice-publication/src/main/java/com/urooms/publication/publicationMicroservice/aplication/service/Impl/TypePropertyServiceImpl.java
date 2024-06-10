package com.urooms.publication.publicationMicroservice.aplication.service.Impl;

import com.urooms.publication.publicationMicroservice.aplication.dto.request.TypePropertyRequestDTO;
import com.urooms.publication.publicationMicroservice.aplication.dto.response.TypePropertyResponseDTO;
import com.urooms.publication.publicationMicroservice.aplication.service.TypePropertyService;
import com.urooms.publication.publicationMicroservice.domain.entities.TypeProperty;
import com.urooms.publication.publicationMicroservice.infraestructure.repositories.TypePropertyRepository;
import com.urooms.publication.shared.model.dto.response.ApiResponse;
import com.urooms.publication.shared.model.enums.Estatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TypePropertyServiceImpl implements TypePropertyService {

    private final TypePropertyRepository typePropertyRepository;

    private final ModelMapper modelMapper;

    public TypePropertyServiceImpl(TypePropertyRepository typePropertyRepository, ModelMapper modelMapper) {
        this.typePropertyRepository = typePropertyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiResponse<TypePropertyResponseDTO> getTypePropertyById(int id) {
        Optional<TypeProperty> typePropertyOptional = typePropertyRepository.findById(id);
        if (typePropertyOptional.isPresent()) {
            TypeProperty typeProperty = typePropertyOptional.get();
            TypePropertyResponseDTO responseDTO = modelMapper.map(typeProperty, TypePropertyResponseDTO.class);
            return new ApiResponse<>("TypeProperty fetched successfully", Estatus.SUCCESS, responseDTO);
        } else {
            return new ApiResponse<>("TypeProperty not found", Estatus.ERROR, null);
        }
    }

    @Override
    public ApiResponse<TypePropertyResponseDTO> createTypeProperty(TypePropertyRequestDTO typePropertyRequestDTO) {
        var typeProperty = modelMapper.map(typePropertyRequestDTO, TypeProperty.class);
        typePropertyRepository.save(typeProperty);
        var response = modelMapper.map(typeProperty, TypePropertyResponseDTO.class);
        return new ApiResponse<>("TypeProperty created successfully", Estatus.SUCCESS, response);
    }

    @Override
    public ApiResponse<TypePropertyResponseDTO> updateTypeProperty(int id, TypePropertyRequestDTO typePropertyRequestDTO) {
        Optional<TypeProperty> typePropertyOptional = typePropertyRepository.findById(id);

        if (typePropertyOptional.isEmpty()) {
            return new ApiResponse<>("TypeProperty not found", Estatus.ERROR, null);
        } else {
            TypeProperty typeProperty = typePropertyOptional.get();
            modelMapper.map(typePropertyRequestDTO, typeProperty);
            typePropertyRepository.save(typeProperty);
            TypePropertyResponseDTO response = modelMapper.map(typeProperty, TypePropertyResponseDTO.class);
            return new ApiResponse<>("TypeProperty updated successfully", Estatus.SUCCESS, response);
        }
    }

    @Override
    public ApiResponse<Void> deleteTypeProperty(int id) {
        Optional<TypeProperty> typePropertyOptional = typePropertyRepository.findById(id);
        if (typePropertyOptional.isPresent()) {
            typePropertyRepository.deleteById(id);
            return new ApiResponse<>("TypeProperty deleted successfully", Estatus.SUCCESS, null);
        } else {
            return new ApiResponse<>("TypeProperty not found", Estatus.ERROR, null);
        }
    }

    @Override
    public ApiResponse<List<TypePropertyResponseDTO>> getTypeProperties() {
        List<TypeProperty> typeProperties = (List<TypeProperty>) typePropertyRepository.findAll();
        List<TypePropertyResponseDTO> responseDTOList = typeProperties.stream()
                .map(entity -> modelMapper.map(entity, TypePropertyResponseDTO.class))
                .collect(Collectors.toList());
        return new ApiResponse<>("TypeProperties fetched successfully", Estatus.SUCCESS, responseDTOList);
    }


}
