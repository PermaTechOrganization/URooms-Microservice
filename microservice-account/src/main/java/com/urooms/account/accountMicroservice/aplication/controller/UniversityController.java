package com.urooms.account.accountMicroservice.aplication.controller;

import com.urooms.account.accountMicroservice.aplication.dto.request.UniversityRequestDTO;
import com.urooms.account.accountMicroservice.aplication.dto.response.CareerResponseDTO;
import com.urooms.account.accountMicroservice.aplication.dto.response.UniversityResponseDTO;
import com.urooms.account.accountMicroservice.aplication.services.UniversityService;
import com.urooms.account.shared.model.dto.response.ApiResponse;
import com.urooms.account.shared.model.enums.Estatus;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Tag(name = "University", description = "University API")
@RestController
@RequestMapping("/api/v1/URooms")
public class UniversityController {

    private final UniversityService universityService;

    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    //@Operation(summary = "get all universities")
    @GetMapping("/universities")
    public ResponseEntity<ApiResponse<List<UniversityResponseDTO>>> getUniversities() {
        var res = universityService.getAllUniversities();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    //@Operation(summary = "get university by id")
    @GetMapping("/universities/{id}")
    public ResponseEntity<ApiResponse<UniversityResponseDTO>> getUniversityById(@PathVariable("id") int id) {
        ApiResponse<UniversityResponseDTO> response = universityService.getUniversityById(id);
        return new ResponseEntity<>(response, response.getStatus() == Estatus.SUCCESS ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    //@Operation(summary = "create a new university")
    @PostMapping("/universities")
    public ResponseEntity<ApiResponse<UniversityResponseDTO>> createUniversity(@RequestBody UniversityRequestDTO universityRequestDto) {
        var res = universityService.createUniversity(universityRequestDto);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    //@Operation(summary = "update an existing university")
    @PutMapping("/universities/{id}")
    public ResponseEntity<ApiResponse<UniversityResponseDTO>> updateUniversity(@PathVariable int id, @RequestBody UniversityRequestDTO universityRequestDto) {
        var res = universityService.updateUniversity(id, universityRequestDto);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

   // @Operation(summary = "delete a university")
    @DeleteMapping("/universities/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUniversity(@PathVariable int id) {
        var res = universityService.deleteUniversity(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
