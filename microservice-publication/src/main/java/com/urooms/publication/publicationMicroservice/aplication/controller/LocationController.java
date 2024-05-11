package com.urooms.publication.publicationMicroservice.aplication.controller;

import com.urooms.publication.publicationMicroservice.aplication.dto.request.LocationRequestDTO;
import com.urooms.publication.publicationMicroservice.aplication.dto.response.LocationResponseDTO;
import com.urooms.publication.publicationMicroservice.aplication.service.LocationService;
import com.urooms.publication.shared.model.dto.response.ApiResponse;
import com.urooms.publication.shared.model.enums.Estatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/URooms")
public class LocationController {

    private final LocationService locationService;
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/locations/{id}")
    public ResponseEntity<ApiResponse<LocationResponseDTO>> getLocationById(@PathVariable("id") int id){
        ApiResponse<LocationResponseDTO> response = locationService.getLocationById(id);
        return new ResponseEntity<>(response, response.getStatus() == Estatus.SUCCESS ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping("/locations")
    public ResponseEntity<ApiResponse<LocationResponseDTO>> createLocation(@RequestBody LocationRequestDTO locationRequestDTO){
        var res = locationService.createLocation(locationRequestDTO);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("/locations/{id}")
    public ResponseEntity<ApiResponse<LocationResponseDTO>> updateLocation(@PathVariable int id, @RequestBody LocationRequestDTO locationRequestDTO){
        var res = locationService.updateLocation(id, locationRequestDTO);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/locations/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteLocation(@PathVariable("id") int id){
        var res = locationService.deleteLocation(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


}
