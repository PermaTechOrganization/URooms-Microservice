package com.urooms.account.accountMicroservice.aplication.services.impl;

import com.urooms.account.accountMicroservice.aplication.dto.request.LessorRequestDTO;
import com.urooms.account.accountMicroservice.aplication.dto.response.LessorResponseDTO;
import com.urooms.account.accountMicroservice.aplication.services.LessorService;
import com.urooms.account.accountMicroservice.domain.entities.Lessor;
import com.urooms.account.accountMicroservice.infraestructure.repositories.AccountRepository;
import com.urooms.account.accountMicroservice.infraestructure.repositories.LessorRepository;
import com.urooms.account.shared.model.dto.response.ApiResponse;
import com.urooms.account.shared.model.enums.Estatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LessorServiceImpl implements LessorService {

    private final LessorRepository lessorRepository;
    private final ModelMapper modelMapper;
    private final AccountRepository accountRepository;

    public LessorServiceImpl(LessorRepository lessorRepository, ModelMapper modelMapper, AccountRepository accountRepository) {
        this.lessorRepository = lessorRepository;
        this.modelMapper = modelMapper;
        this.accountRepository = accountRepository;
    }

    @Override
    public ApiResponse<List<LessorResponseDTO>> getLessors() {
        List<Lessor> lessorsList = (List<Lessor>) lessorRepository.findAll();
        List<LessorResponseDTO> lessorResponseDTOList = lessorsList.stream()
                .map(entity -> modelMapper.map(entity, LessorResponseDTO.class))
                .collect(Collectors.toList());

        return new ApiResponse<>("All lessors fetched successfully", Estatus.SUCCESS,lessorResponseDTOList);
    }

    @Override
    public ApiResponse<LessorResponseDTO> getLessorById(int id) {
        Optional<Lessor> lessorOptional = lessorRepository.findById(id);
        if (lessorOptional.isPresent()){
            Lessor lessor = lessorOptional.get();
            LessorResponseDTO responseDTO = modelMapper.map(lessor, LessorResponseDTO.class);
            return new ApiResponse<>("Lessor fetched successfully", Estatus.SUCCESS, responseDTO);
        }else {
            return new ApiResponse<>("Lessor not found", Estatus.ERROR, null);
        }
    }

    @Override
    public ApiResponse<LessorResponseDTO> createLessor(LessorRequestDTO lessorRequestDTO) {
        var lessor = modelMapper.map(lessorRequestDTO, Lessor.class);
        lessor.setAccount(accountRepository.getAccountById(lessorRequestDTO.getAccount()));
        lessorRepository.save(lessor);

        var response = modelMapper.map(lessor, LessorResponseDTO.class);

        return new ApiResponse<>("Lessor created successfully", Estatus.SUCCESS, response);
    }

    @Override
    public ApiResponse<LessorResponseDTO> updateLessor(int id, LessorRequestDTO lessorRequestDTO) {
        Optional<Lessor> lessorOptional = lessorRepository.findById(id);

        if (lessorOptional.isEmpty()) {
            return new ApiResponse<>("Lessor not found", Estatus.ERROR, null);
        }else {
            Lessor lessor = lessorOptional.get();
            modelMapper.map(lessorRequestDTO, lessor);
            lessor.setAccount(accountRepository.getAccountById(lessorRequestDTO.getAccount()));
            lessorRepository.save(lessor);
            LessorResponseDTO response = modelMapper.map(lessor, LessorResponseDTO.class);
            return new ApiResponse<>("Lessor updated successfully", Estatus.SUCCESS, response);
        }
    }

    @Override
    public ApiResponse<Void> deleteLessor(int id) {
        Optional<Lessor> lessorOptional = lessorRepository.findById(id);
        if (lessorOptional.isEmpty()) {
            return new ApiResponse<>("Lessor not found", Estatus.ERROR, null);
        }else {
            lessorRepository.deleteById(id);
            return new ApiResponse<>("Lessor deleted successfully", Estatus.SUCCESS, null);
        }
    }
}
