package com.urooms.publication.publicationMicroservice.infraestructure.repositories;

import com.urooms.publication.publicationMicroservice.domain.entities.Publication;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PublicationRepository extends CrudRepository<Publication, Integer> {

    Publication getPublicationById(int id);

    List<Publication> getPublicationByLessorId(int lessorId);

    List<Publication> getPublicationByTypePropertyId(int typePropertyId);
}
