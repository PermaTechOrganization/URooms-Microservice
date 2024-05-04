package com.urooms.account.accountMicroservice.aplication.controller;

import com.urooms.account.accountMicroservice.aplication.dto.request.CareerRequestDTO;
import com.urooms.account.accountMicroservice.aplication.dto.response.CareerResponseDTO;
import com.urooms.account.accountMicroservice.aplication.services.CareerService;
import com.urooms.account.shared.model.dto.response.ApiResponse;
import com.urooms.account.shared.model.enums.Estatus;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Tag(name = "Career", description = "Career API")
@RestController
@RequestMapping("/api/v1/URooms")
public class CareerController {

    private final CareerService careerService;

    public CareerController(CareerService careerService) {
        this.careerService = careerService;
    }

    //@Operation(summary = "get all careers")
    @GetMapping("/careers")
    public ResponseEntity<ApiResponse<List<CareerResponseDTO>>> getAllCareers() {
        var res = careerService.getAllCareers();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    //@Operation(summary = "get career by id")
    @GetMapping("/careers/{id}")
    public ResponseEntity<ApiResponse<CareerResponseDTO>> getCareerById(@PathVariable("id") int id) {
        ApiResponse<CareerResponseDTO> response = careerService.getCareerById(id);
        return new ResponseEntity<>(response, response.getStatus() == Estatus.SUCCESS ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    //@Operation(summary = "create a new career")
    @PostMapping("/careers")
    public ResponseEntity<ApiResponse<CareerResponseDTO>> createCareer(@RequestBody CareerRequestDTO careerRequestDto) {
        var res = careerService.createCareer(careerRequestDto);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    //@Operation(summary = "update an existing career")
    @PutMapping("/careers/{id}")
    public ResponseEntity<ApiResponse<CareerResponseDTO>> updateCareer(@PathVariable int id, @RequestBody CareerRequestDTO careerRequestDto) {
        var res = careerService.updateCareer(id, careerRequestDto);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    //@Operation(summary = "delete a career")
    @DeleteMapping("/careers/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCareer(@PathVariable int id) {
        var res = careerService.deleteCareer(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
