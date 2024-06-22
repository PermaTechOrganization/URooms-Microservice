package com.urooms.management.managementMicroservice.application.service.impl;

import com.urooms.management.client.LessorClient;
import com.urooms.management.client.StudentClient;
import com.urooms.management.events.service.KafkaProducerService;
import com.urooms.management.managementMicroservice.application.dto.request.RentalContractRequestDTO;
import com.urooms.management.managementMicroservice.application.dto.response.RentalContractResponseDTO;
import com.urooms.management.managementMicroservice.application.service.RentalContractService;
import com.urooms.management.managementMicroservice.domain.entities.RentalContract;
import com.urooms.management.managementMicroservice.domain.events.RentalCreateEvent;
import com.urooms.management.managementMicroservice.infraestructure.repositories.RentalContractRepository;
import com.urooms.management.shared.model.dto.response.ApiResponse;
import com.urooms.management.shared.model.enums.Estatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentalContractServiceImpl implements RentalContractService {

    private final RentalContractRepository rentalContractRepository;
    private final ModelMapper modelMapper;
    private final LessorClient lessorClient;
    private final StudentClient studentClient;
    private final KafkaProducerService kafkaProducerService;

    public RentalContractServiceImpl(RentalContractRepository rentalContractRepository, KafkaProducerService kafkaProducerService, ModelMapper modelMapper, LessorClient lessorClient, StudentClient studentClient) {
        this.rentalContractRepository = rentalContractRepository;
        this.modelMapper = modelMapper;
        this.lessorClient = lessorClient;
        this.studentClient = studentClient;
        this.kafkaProducerService = kafkaProducerService;
    }

    @Override
    public ApiResponse<RentalContractResponseDTO> getRentalContractById(int id) {
        Optional<RentalContract> rentalContractOptional = rentalContractRepository.findById(id);
        if(rentalContractOptional.isPresent()){
            RentalContract rentalContract = rentalContractOptional.get();
            RentalContractResponseDTO responseDTO =
                    RentalContractResponseDTO.builder()
                            .id(rentalContract.getId())
                            .lessor(lessorClient.getLessorById(rentalContract.getLessor()))
                            .student(studentClient.getStudentById(rentalContract.getStudent()))
                            .build();
            return new ApiResponse<>("Rental Contract fetched successfully", Estatus.SUCCESS,responseDTO);
        } else {
            return new ApiResponse<>("Rental Contract not found", Estatus.ERROR,null);
        }
    }

    @Override
    public ApiResponse<Void> deleteRentalContract(int id) {
        Optional<RentalContract> rentalContractOptional = rentalContractRepository.findById(id);
        if (rentalContractOptional.isPresent()) {
            rentalContractRepository.deleteById(id);
            return new ApiResponse<>("Rental Contract deleted successfully", Estatus.SUCCESS, null);
        } else {
            return new ApiResponse<>("Rental Contract not found", Estatus.ERROR, null);
        }
    }

    @Override
    public ApiResponse<RentalContractResponseDTO> createRentalContract(RentalContractRequestDTO rentalContractRequestDTO) {
        var rentalContract = modelMapper.map(rentalContractRequestDTO, RentalContract.class);

        if(lessorClient.getLessorById(rentalContract.getLessor()) == null || studentClient.getStudentById(rentalContract.getStudent()) == null){
            return new ApiResponse<>("Lessor or Student not found", Estatus.ERROR, null);
        }

        rentalContractRepository.save(rentalContract);
        RentalContractResponseDTO responseDTO = modelMapper.map(rentalContract, RentalContractResponseDTO.class);
        responseDTO.setLessor(lessorClient.getLessorById(rentalContract.getLessor()));
        responseDTO.setStudent(studentClient.getStudentById(rentalContract.getStudent()));

        kafkaProducerService.publishRentalCreatedEvent(new RentalCreateEvent(responseDTO.getId(), responseDTO.getPrice()));

        return new ApiResponse<>("Rental Contract created successfully", Estatus.SUCCESS, responseDTO);
    }

    @Override
    public ApiResponse<RentalContractResponseDTO> updateRentalContract(int id, RentalContractRequestDTO rentalContractRequestDTO) {
        Optional<RentalContract> rentalContractOptional = rentalContractRepository.findById(id);

        if(rentalContractOptional.isPresent()){
            var rentalContract = rentalContractOptional.get();
            modelMapper.map(rentalContractRequestDTO, rentalContract);
            rentalContractRepository.save(rentalContract);
            RentalContractResponseDTO responseDTO = modelMapper.map(rentalContract, RentalContractResponseDTO.class);
            responseDTO.setLessor(lessorClient.getLessorById(rentalContract.getLessor()));
            responseDTO.setStudent(studentClient.getStudentById(rentalContract.getStudent()));
            return new ApiResponse<>("Rental Contract updated successfully", Estatus.SUCCESS, responseDTO);
        } else {
            return new ApiResponse<>("Rental Contract not found", Estatus.ERROR, null);
        }
    }

    @Override
    public ApiResponse<List<RentalContractResponseDTO>> getRentalContractByLessor(int lessor) {
        List<RentalContract> rentalContracts = rentalContractRepository.getRentalContractByLessor(lessor);
        List<RentalContractResponseDTO> responseDTOs = rentalContracts.stream()
                .map(rentalContract -> {
                    RentalContractResponseDTO responseDTO = modelMapper.map(rentalContract, RentalContractResponseDTO.class);
                    responseDTO.setLessor(lessorClient.getLessorById(rentalContract.getLessor()));
                    responseDTO.setStudent(studentClient.getStudentById(rentalContract.getStudent()));
                    return responseDTO;
                }).toList();
        return new ApiResponse<>("Rental Contracts fetched successfully", Estatus.SUCCESS, responseDTOs);
    }

    @Override
    public ApiResponse<List<RentalContractResponseDTO>> getRentalContractByStudent(int student) {
        List<RentalContract> rentalContracts = rentalContractRepository.getRentalContractByStudent(student);
        List<RentalContractResponseDTO> responseDTOs = rentalContracts.stream()
                .map(rentalContract -> {
                    RentalContractResponseDTO responseDTO = modelMapper.map(rentalContract, RentalContractResponseDTO.class);
                    responseDTO.setLessor(lessorClient.getLessorById(rentalContract.getLessor()));
                    responseDTO.setStudent(studentClient.getStudentById(rentalContract.getStudent()));
                    return responseDTO;
                }).toList();
        return new ApiResponse<>("Rental Contracts fetched successfully", Estatus.SUCCESS, responseDTOs);
    }

}
