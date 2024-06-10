package com.urooms.publication.publicationMicroservice.aplication.controller;

import com.urooms.publication.publicationMicroservice.aplication.dto.request.DistrictRequestDTO;
import com.urooms.publication.publicationMicroservice.aplication.dto.response.DistrictResponseDTO;
import com.urooms.publication.publicationMicroservice.aplication.service.DistrictService;
import com.urooms.publication.shared.model.dto.response.ApiResponse;
import com.urooms.publication.shared.model.enums.Estatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/URooms")
public class DistrictController {
    private final DistrictService districtService;

    public DistrictController(DistrictService districtService) {
        this.districtService = districtService;
    }

    @GetMapping("/districts")
    public ResponseEntity<ApiResponse<List<DistrictResponseDTO>>> getAllDistricts(){
        var res = districtService.getDistricts();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/districts/{id}")
    public ResponseEntity<ApiResponse<DistrictResponseDTO>> getDistrictById(@PathVariable("id") int id){
        ApiResponse<DistrictResponseDTO> response = districtService.getDistrictById(id);
        return new ResponseEntity<>(response, response.getStatus() == Estatus.SUCCESS ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping("/districts")
    public ResponseEntity<ApiResponse<DistrictResponseDTO>> createDistrict(@RequestBody DistrictRequestDTO districtRequestDTO){
        var res = districtService.createDistrict(districtRequestDTO);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("/districts/{id}")
    public ResponseEntity<ApiResponse<DistrictResponseDTO>> updateDistrict(@PathVariable int id, @RequestBody DistrictRequestDTO districtRequestDTO){
        var res = districtService.updateDistrict(id, districtRequestDTO);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/districts/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteDistrict(@PathVariable("id") int id){
        var res = districtService.deleteDistrict(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
