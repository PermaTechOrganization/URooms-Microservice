package com.urooms.management.managementMicroservice.application.controller;

import com.urooms.management.managementMicroservice.application.dto.request.RentalContractRequestDTO;
import com.urooms.management.managementMicroservice.application.dto.response.RentalContractResponseDTO;
import com.urooms.management.managementMicroservice.application.service.RentalContractService;
import com.urooms.management.shared.model.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v4/URooms")
public class RentalContractController {

    private final RentalContractService rentalContractService;

    public RentalContractController(RentalContractService rentalContractService) {
        this.rentalContractService = rentalContractService;
    }

    @GetMapping("/rental-contracts/{id}")
    public ResponseEntity<ApiResponse<RentalContractResponseDTO>> getRentalContractById(@PathVariable("id") int id) {
        return ResponseEntity.ok(rentalContractService.getRentalContractById(id));
    }

    @DeleteMapping("/rental-contracts/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteRentalContract(@PathVariable("id") int id){
        return ResponseEntity.ok(rentalContractService.deleteRentalContract(id));
    }

    @PostMapping("/rental-contracts")
    public ResponseEntity<ApiResponse<RentalContractResponseDTO>> createRentalContract(@RequestBody RentalContractRequestDTO rentalContractRequestDTO) {
        return ResponseEntity.ok(rentalContractService.createRentalContract(rentalContractRequestDTO));
    }

    @PutMapping("/rental-contracts/{id}")
    public ResponseEntity<ApiResponse<RentalContractResponseDTO>> updateRentalContract(@PathVariable("id") int id, @RequestBody RentalContractRequestDTO rentalContractRequestDTO) {
        return ResponseEntity.ok(rentalContractService.updateRentalContract(id, rentalContractRequestDTO));
    }

    @GetMapping("/rental-contracts/lessor/{lessor}")
    public ResponseEntity<ApiResponse<List<RentalContractResponseDTO>>> getRentalContractByLessor(@PathVariable("lessor") int lessor) {
        return ResponseEntity.ok(rentalContractService.getRentalContractByLessor(lessor));
    }

    @GetMapping("/rental-contracts/student/{student}")
    public ResponseEntity<ApiResponse<List<RentalContractResponseDTO>>> getRentalContractByStudent(@PathVariable("student") int student) {
        return ResponseEntity.ok(rentalContractService.getRentalContractByStudent(student));
    }
}
