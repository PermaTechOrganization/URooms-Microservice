package com.urooms.publication.publicationMicroservice.aplication.controller;

import com.urooms.publication.publicationMicroservice.aplication.dto.request.TypePropertyRequestDTO;
import com.urooms.publication.publicationMicroservice.aplication.dto.response.TypePropertyResponseDTO;
import com.urooms.publication.publicationMicroservice.aplication.service.TypePropertyService;
import com.urooms.publication.shared.model.dto.response.ApiResponse;
import com.urooms.publication.shared.model.enums.Estatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/URooms")
public class TypePropertyController {

    private final TypePropertyService typePropertyService;

    public TypePropertyController(TypePropertyService typePropertyService) {
        this.typePropertyService = typePropertyService;
    }

    @GetMapping("/typeProperties")
    public ResponseEntity<ApiResponse<List<TypePropertyResponseDTO>>> getAllTypeProperties(){
        var res = typePropertyService.getTypeProperties();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/typeProperties/{id}")
    public ResponseEntity<ApiResponse<TypePropertyResponseDTO>> getTypePropertyById(@PathVariable("id") int id){
        ApiResponse<TypePropertyResponseDTO> response = typePropertyService.getTypePropertyById(id);
        return new ResponseEntity<>(response, response.getStatus() == Estatus.SUCCESS ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping("/typeProperties")
    public ResponseEntity<ApiResponse<TypePropertyResponseDTO>> createTypeProperty(@RequestBody TypePropertyRequestDTO typePropertyRequestDTO){
        var res = typePropertyService.createTypeProperty(typePropertyRequestDTO);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("/typeProperties/{id}")
    public ResponseEntity<ApiResponse<TypePropertyResponseDTO>> updateTypeProperty(@PathVariable int id, @RequestBody TypePropertyRequestDTO typePropertyRequestDTO){
        var res = typePropertyService.updateTypeProperty(id, typePropertyRequestDTO);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/typeProperties/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteTypeProperty(@PathVariable("id") int id){
        var res = typePropertyService.deleteTypeProperty(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
