package com.urooms.account.accountMicroservice.aplication.services.impl;

import com.urooms.account.accountMicroservice.aplication.dto.request.LessorRequestDTO;
import com.urooms.account.accountMicroservice.aplication.dto.response.AccountResponseDTO;
import com.urooms.account.accountMicroservice.aplication.dto.response.LessorClientResponseDTO;
import com.urooms.account.accountMicroservice.aplication.dto.response.LessorResponseDTO;
import com.urooms.account.accountMicroservice.aplication.services.LessorService;
import com.urooms.account.accountMicroservice.domain.entities.Account;
import com.urooms.account.accountMicroservice.domain.entities.Lessor;
import com.urooms.account.accountMicroservice.infraestructure.repositories.AccountRepository;
import com.urooms.account.accountMicroservice.infraestructure.repositories.LessorRepository;
import com.urooms.account.shared.model.dto.response.ApiResponse;
import com.urooms.account.shared.model.enums.Estatus;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LessorServiceImpl implements LessorService {

    private final String REAL_NAME = "urooms";
    private final Keycloak keycloak;
    private final LessorRepository lessorRepository;
    private final ModelMapper modelMapper;
    private final AccountRepository accountRepository;

    public LessorServiceImpl(LessorRepository lessorRepository, ModelMapper modelMapper, AccountRepository accountRepository, Keycloak keycloak) {
        this.lessorRepository = lessorRepository;
        this.modelMapper = modelMapper;
        this.accountRepository = accountRepository;
        this.keycloak = keycloak;
    }

    @Override
    public ApiResponse<LessorClientResponseDTO> getLessorClientById(int id) {
        LessorResponseDTO lessorResponseDTO = getLessorById(id).getData();

        if (lessorResponseDTO != null) {
            LessorClientResponseDTO lessorClientResponseDTO =
                    LessorClientResponseDTO.builder()
                            .id(lessorResponseDTO.getId())
                            .firstName(lessorResponseDTO.getFirstName())
                            .lastName(lessorResponseDTO.getLastName())
                            .dni(lessorResponseDTO.getDni())
                            .phone(lessorResponseDTO.getPhone())
                            .photoUrl(lessorResponseDTO.getPhotoUrl()).build();
            return new ApiResponse<>("Lessor fetched successfully", Estatus.SUCCESS, lessorClientResponseDTO);
        } else {
            return new ApiResponse<>("Lessor not found", Estatus.ERROR, null);
        }
    }

    @Override
    public ApiResponse<List<LessorResponseDTO>> getLessors() {
        List<Lessor> lessorsList = (List<Lessor>) lessorRepository.findAll();
        List<LessorResponseDTO> lessorResponseDTOList = lessorsList.stream()
                .map(entity -> {
                    LessorResponseDTO lessorResponseDTO = modelMapper.map(entity, LessorResponseDTO.class);

                    UserRepresentation userRepresentation = keycloak.realm(REAL_NAME)
                            .users()
                            .get(entity.getAccount())
                            .toRepresentation();

                    AccountResponseDTO accountResponseDTO = modelMapper.map(userRepresentation, AccountResponseDTO.class);

                    lessorResponseDTO.setAccount(accountResponseDTO);

                    return lessorResponseDTO;

                })
                .collect(Collectors.toList());

        return new ApiResponse<>("All lessors fetched successfully", Estatus.SUCCESS,lessorResponseDTOList);
    }

    @Override
    public ApiResponse<LessorResponseDTO> getLessorById(int id) {
        Optional<Lessor> lessorOptional = lessorRepository.findById(id);
        if (lessorOptional.isPresent()){
            Lessor lessor = lessorOptional.get();

            UserRepresentation userRepresentation = keycloak.realm(REAL_NAME)
                    .users()
                    .get(lessor.getAccount())
                    .toRepresentation();

            AccountResponseDTO accountResponseDTO = modelMapper.map(userRepresentation, AccountResponseDTO.class);

            LessorResponseDTO responseDTO = modelMapper.map(lessor, LessorResponseDTO.class);

            responseDTO.setAccount(accountResponseDTO);

            return new ApiResponse<>("Lessor fetched successfully", Estatus.SUCCESS, responseDTO);
        }else {
            return new ApiResponse<>("Lessor not found", Estatus.ERROR, null);
        }
    }

    @Override
    public ApiResponse<LessorResponseDTO> createLessor(LessorRequestDTO lessorRequestDTO) {

        UserRepresentation userRepresentation = keycloak.realm(REAL_NAME)
                .users()
                .get(lessorRequestDTO.getAccount())
                .toRepresentation();

        if (userRepresentation == null) {
            return new ApiResponse<>("Account not found", Estatus.ERROR, null);
        }else {
            AccountResponseDTO accountResponseDTO = modelMapper.map(userRepresentation, AccountResponseDTO.class);

            var lessor = modelMapper.map(lessorRequestDTO, Lessor.class);

            lessorRepository.save(lessor);

            var response = modelMapper.map(lessor, LessorResponseDTO.class);

            response.setAccount(accountResponseDTO);

            return new ApiResponse<>("Lessor created successfully", Estatus.SUCCESS, response);
        }

    }

    @Override
    public ApiResponse<LessorResponseDTO> updateLessor(int id, LessorRequestDTO lessorRequestDTO) {
        Optional<Lessor> lessorOptional = lessorRepository.findById(id);

        if (lessorOptional.isEmpty()) {
            return new ApiResponse<>("Lessor not found", Estatus.ERROR, null);
        }else {
            Lessor lessor = lessorOptional.get();

            UserRepresentation userRepresentation = keycloak.realm(REAL_NAME)
                    .users()
                    .get(lessor.getAccount())
                    .toRepresentation();

            AccountResponseDTO accountResponseDTO = modelMapper.map(userRepresentation, AccountResponseDTO.class);

            modelMapper.map(lessorRequestDTO, lessor);
            lessorRepository.save(lessor);
            LessorResponseDTO response = modelMapper.map(lessor, LessorResponseDTO.class);
            response.setAccount(accountResponseDTO);
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
