package com.urooms.publication.publicationMicroservice.infraestructure.repositories;

import com.urooms.publication.publicationMicroservice.domain.entities.District;
import org.springframework.data.repository.CrudRepository;

public interface DistrictRepository extends CrudRepository<District, Integer> {

}
