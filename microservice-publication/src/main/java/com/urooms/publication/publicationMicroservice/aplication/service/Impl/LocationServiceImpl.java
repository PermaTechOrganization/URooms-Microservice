package com.urooms.publication.publicationMicroservice.aplication.service.Impl;

import com.urooms.publication.publicationMicroservice.aplication.dto.request.LocationRequestDTO;
import com.urooms.publication.publicationMicroservice.aplication.dto.response.LocationResponseDTO;
import com.urooms.publication.publicationMicroservice.aplication.service.LocationService;
import com.urooms.publication.publicationMicroservice.domain.entities.Location;
import com.urooms.publication.publicationMicroservice.infraestructure.repositories.LocationRepository;
import com.urooms.publication.publicationMicroservice.infraestructure.repositories.PublicationRepository;
import com.urooms.publication.shared.model.dto.response.ApiResponse;
import com.urooms.publication.shared.model.enums.Estatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    private final PublicationRepository publicationRepository;
    private final ModelMapper modelMapper;

    public LocationServiceImpl(LocationRepository locationRepository, ModelMapper modelMapper, PublicationRepository publicationRepository) {
        this.locationRepository = locationRepository;
        this.modelMapper = modelMapper;
        this.publicationRepository = publicationRepository;
    }

    @Override
    public ApiResponse<LocationResponseDTO> getLocationById(int id) {
        Optional<Location> locationOptional = locationRepository.findById(id);
        if (locationOptional.isPresent()) {
            Location location = locationOptional.get();
            LocationResponseDTO responseDTO = modelMapper.map(location, LocationResponseDTO.class);
            return new ApiResponse<>("Location fetched successfully", Estatus.SUCCESS, responseDTO);
        } else {
            return new ApiResponse<>("Location not found", Estatus.ERROR, null);
        }
    }

    @Override
    public ApiResponse<LocationResponseDTO> createLocation(LocationRequestDTO locationRequestDTO) {
        var location = modelMapper.map(locationRequestDTO, Location.class);
        location.setPublication(publicationRepository.getPublicationById(locationRequestDTO.getPublication()));
        locationRepository.save(location);
        var response = modelMapper.map(location, LocationResponseDTO.class);
        return new ApiResponse<>("Location created successfully", Estatus.SUCCESS, response);
    }

    @Override
    public ApiResponse<LocationResponseDTO> updateLocation(int id, LocationRequestDTO locationRequestDTO) {
        Optional<Location> locationOptional = locationRepository.findById(id);

        if (locationOptional.isEmpty()) {
            return new ApiResponse<>("Location not found", Estatus.ERROR, null);
        } else {
            Location location = locationOptional.get();
            modelMapper.map(locationRequestDTO, location);
            location.setPublication(publicationRepository.getPublicationById(locationRequestDTO.getPublication()));
            locationRepository.save(location);
            LocationResponseDTO response = modelMapper.map(location, LocationResponseDTO.class);
            return new ApiResponse<>("Location updated successfully", Estatus.SUCCESS, response);
        }
    }

    @Override
    public ApiResponse<Void> deleteLocation(int id) {
        Optional<Location> locationOptional = locationRepository.findById(id);
        if (locationOptional.isPresent()) {
            locationRepository.deleteById(id);
            return new ApiResponse<>("Location deleted successfully", Estatus.SUCCESS, null);
        } else {
            return new ApiResponse<>("Location not found", Estatus.ERROR, null);
        }
    }
}
