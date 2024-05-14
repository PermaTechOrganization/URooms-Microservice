package com.urooms.publication.publicationMicroservice.aplication.service.Impl;

import com.urooms.publication.publicationMicroservice.aplication.dto.request.PublicationRequestDTO;
import com.urooms.publication.publicationMicroservice.aplication.dto.response.LessorResponseDTO;
import com.urooms.publication.publicationMicroservice.aplication.dto.response.PublicationResponseDTO;
import com.urooms.publication.publicationMicroservice.aplication.service.PublicationService;
import com.urooms.publication.client.LessorClient;
import com.urooms.publication.publicationMicroservice.domain.entities.Publication;
import com.urooms.publication.publicationMicroservice.infraestructure.repositories.PublicationRepository;
import com.urooms.publication.publicationMicroservice.infraestructure.repositories.TypePropertyRepository;
import com.urooms.publication.shared.model.dto.response.ApiResponse;
import com.urooms.publication.shared.model.enums.Estatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PublicationServiceImpl implements PublicationService {

    private final PublicationRepository publicationRepository;

    private final TypePropertyRepository typePropertyRepository;

    private final LessorClient lessorClient;

    private final ModelMapper modelMapper;

    public PublicationServiceImpl(PublicationRepository publicationRepository, LessorClient lessorClient,ModelMapper modelMapper, TypePropertyRepository typePropertyRepository) {
        this.publicationRepository = publicationRepository;
        this.modelMapper = modelMapper;
        this.typePropertyRepository = typePropertyRepository;
        this.lessorClient = lessorClient;
    }

    @Override
    public ApiResponse<PublicationResponseDTO> getPublicationById(int id) {
        Optional<Publication> publicationOptional = publicationRepository.findById(id);
        if (publicationOptional.isPresent()) {
            Publication publication = publicationOptional.get();
            PublicationResponseDTO responseDTO =
                PublicationResponseDTO.builder()
                        .id(publication.getId())
                        .title(publication.getTitle())
                        .description(publication.getDescription())
                        .imageURL(publication.getImageURL())
                        .price(publication.getPrice())
                        .rating(publication.getRating())
                        .lessor(lessorClient.getLessorById(publication.getLessorId()))
                        .typeProperty(publication.getTypeProperty())
                        .build();

            var prueba = lessorClient.getLessorById(publication.getLessorId());

            return new ApiResponse<>("Publication fetched successfully", Estatus.SUCCESS, responseDTO);
        } else {
            return new ApiResponse<>("Publication not found", Estatus.ERROR, null);
        }
    }

    @Override
    public ApiResponse<PublicationResponseDTO> createPublication(PublicationRequestDTO publicationRequestDTO) {
        var publication = modelMapper.map(publicationRequestDTO, Publication.class);
        publication.setLessorId(publicationRequestDTO.getLessor());
        publication.setTypeProperty(typePropertyRepository.getTypePropertyById(publicationRequestDTO.getTypeProperty()));
        if(lessorClient.getLessorById(publicationRequestDTO.getLessor()) == null){
            return new ApiResponse<>("Lessor not found", Estatus.ERROR, null);
        }else {
            publicationRepository.save(publication);
            var response = modelMapper.map(publication, PublicationResponseDTO.class);
            response.setLessor(lessorClient.getLessorById(publicationRequestDTO.getLessor()));
            return new ApiResponse<>("Publication created successfully", Estatus.SUCCESS, response);
        }
    }

    @Override
    public ApiResponse<PublicationResponseDTO> updatePublication(int id, PublicationRequestDTO publicationRequestDTO) {
        Optional<Publication> publicationOptional = publicationRepository.findById(id);

        if (publicationOptional.isEmpty()) {
            return new ApiResponse<>("Publication not found", Estatus.ERROR, null);
        } else {
            Publication publication = publicationOptional.get();
            modelMapper.map(publicationRequestDTO, publication);
            publication.setLessorId(publicationRequestDTO.getLessor());
            publication.setTypeProperty(typePropertyRepository.getTypePropertyById(publicationRequestDTO.getTypeProperty()));
            publicationRepository.save(publication);
            PublicationResponseDTO response = modelMapper.map(publication, PublicationResponseDTO.class);
            return new ApiResponse<>("Publication updated successfully", Estatus.SUCCESS, response);
        }
    }

    @Override
    public ApiResponse<Void> deletePublication(int id) {
        Optional<Publication> publicationOptional = publicationRepository.findById(id);
        if (publicationOptional.isPresent()) {
            publicationRepository.deleteById(id);
            return new ApiResponse<>("Publication deleted successfully", Estatus.SUCCESS, null);
        } else {
            return new ApiResponse<>("Publication not found", Estatus.ERROR, null);
        }
    }

    @Override
    public ApiResponse<List<PublicationResponseDTO>> getPublications() {
        List<Publication> publications = (List<Publication>) publicationRepository.findAll();
        List<PublicationResponseDTO> responseDTO = publications.stream()
                .map(publication ->{
                        PublicationResponseDTO dto = modelMapper.map(publication, PublicationResponseDTO.class);
                        dto.setLessor(lessorClient.getLessorById(publication.getLessorId()));
                        return dto;
                })
                .collect(Collectors.toList());
        return new ApiResponse<>("Publications fetched successfully", Estatus.SUCCESS, responseDTO);
    }

    @Override
    public ApiResponse<List<PublicationResponseDTO>> getPublicationByLessorId(int lessorId) {
        List<Publication> publicationsList = publicationRepository.getPublicationByLessorId(lessorId);

        List<PublicationResponseDTO> responseDTOList = publicationsList.stream()
                .map(publication -> {
                    PublicationResponseDTO dto = modelMapper.map(publication, PublicationResponseDTO.class);
                    dto.setLessor(lessorClient.getLessorById(publication.getLessorId()));
                    return dto;
                })
                .collect(Collectors.toList());

        return new ApiResponse<>("Publications fetched successfully", Estatus.SUCCESS, responseDTOList);
    }

    @Override
    public ApiResponse<List<PublicationResponseDTO>> getPublicationByTypePropertyId(int typePropertyId) {
        List<Publication> publicationsList = publicationRepository.getPublicationByTypePropertyId(typePropertyId);

        List<PublicationResponseDTO> responseDTOList = publicationsList.stream()
                .map(publication -> {
                    PublicationResponseDTO dto = modelMapper.map(publication, PublicationResponseDTO.class);
                    dto.setLessor(lessorClient.getLessorById(publication.getLessorId()));
                    return dto;
                })
                .collect(Collectors.toList());

        return new ApiResponse<>("Publications fetched successfully", Estatus.SUCCESS, responseDTOList);
    }

    @Override
    public ApiResponse<LessorResponseDTO> getLessorById(int id) {

        LessorResponseDTO lessorResponseDTO = lessorClient.getLessorById(id);
        return new ApiResponse<>("Lessor fetched successfully", Estatus.SUCCESS, lessorResponseDTO);

    }

}
