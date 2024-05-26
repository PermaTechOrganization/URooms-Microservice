package com.urooms.publication.publicationMicroservice.aplication.controller;

import com.urooms.publication.publicationMicroservice.aplication.dto.request.PublicationRequestDTO;
import com.urooms.publication.publicationMicroservice.aplication.dto.response.PublicationClientResponseDTO;
import com.urooms.publication.publicationMicroservice.aplication.dto.response.PublicationResponseDTO;
import com.urooms.publication.publicationMicroservice.aplication.service.PublicationService;
import com.urooms.publication.shared.model.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/URooms")
public class PublicationController {

    private final PublicationService publicationService;

    public PublicationController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    @GetMapping("/publications")
    public ResponseEntity<ApiResponse<List<PublicationResponseDTO>>> getAllPublications() {
        var res = publicationService.getPublications();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/publications/lessor/{id}")
    public ResponseEntity<ApiResponse<List<PublicationResponseDTO>>> getPublicationsByLessor(@PathVariable("id") int id) {
        var res = publicationService.getPublicationByLessorId(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/publications/typeProperty/{id}")
    public ResponseEntity<ApiResponse<List<PublicationResponseDTO>>> getPublicationsByTypeProperty(@PathVariable("id") int id) {
        var res = publicationService.getPublicationByTypePropertyId(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/publications/{id}")
    public ResponseEntity<ApiResponse<PublicationResponseDTO>> getPublicationsById(@PathVariable("id") int id) {
        var res = publicationService.getPublicationById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/publications")
    public ResponseEntity<ApiResponse<PublicationResponseDTO>> createPublication(@RequestBody PublicationRequestDTO publicationRequestDTO) {
        var res = publicationService.createPublication(publicationRequestDTO);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("/publications/{id}")
    public ResponseEntity<ApiResponse<PublicationResponseDTO>> updatePublication(@PathVariable int id, @RequestBody PublicationRequestDTO publicationRequestDTO) {
        var res = publicationService.updatePublication(id, publicationRequestDTO);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/publications/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePublication(@PathVariable int id) {
        var res = publicationService.deletePublication(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/publicationsClient/{id}")
    public PublicationClientResponseDTO getPublicationClientById(@PathVariable("id") int id) {
        return publicationService.getPublicationClientById(id).getData();
    }

}
