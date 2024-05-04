package com.urooms.account.accountMicroservice.aplication.controller;

import com.urooms.account.accountMicroservice.aplication.dto.request.LessorRequestDTO;
import com.urooms.account.accountMicroservice.aplication.dto.response.LessorResponseDTO;
import com.urooms.account.accountMicroservice.aplication.services.LessorService;
import com.urooms.account.shared.model.dto.response.ApiResponse;
import com.urooms.account.shared.model.enums.Estatus;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Tag(name = "Lessor", description = "Lessor API")
@RestController
@RequestMapping("/api/v1/URooms")
public class LessorController {

    private final LessorService lessorService;

    public LessorController(LessorService lessorService) {
        this.lessorService = lessorService;
    }

    //@Operation(summary = "get all lessors")
    @GetMapping("/lessors")
    public ResponseEntity<ApiResponse<List<LessorResponseDTO>>> getLessors() {
        var res = lessorService.getLessors();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    //@Operation(summary = "get a lessor by id")
    @GetMapping("/lessors/{id}")
    public ResponseEntity<ApiResponse<LessorResponseDTO>> getLessorById(@PathVariable("id") int id) {
        ApiResponse<LessorResponseDTO> response = lessorService.getLessorById(id);
        return new ResponseEntity<>(response, response.getStatus() == Estatus.SUCCESS ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    //@Operation(summary = "create a new lessor")
    @PostMapping("/lessors")
    public ResponseEntity<ApiResponse<LessorResponseDTO>> createLessor(@RequestBody LessorRequestDTO lessorRequestDTO) {
        var res = lessorService.createLessor(lessorRequestDTO);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    //@Operation(summary = "update an existing lessor")
    @PutMapping("/lessors/{id}")
    public ResponseEntity<ApiResponse<LessorResponseDTO>> updateLessor(@PathVariable int id, @RequestBody LessorRequestDTO lessorRequestDTO) {
        var res = lessorService.updateLessor(id, lessorRequestDTO);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    //@Operation(summary = "delete a lessor")
    @DeleteMapping("/lessors/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteLessor(@PathVariable int id) {
        var res = lessorService.deleteLessor(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
