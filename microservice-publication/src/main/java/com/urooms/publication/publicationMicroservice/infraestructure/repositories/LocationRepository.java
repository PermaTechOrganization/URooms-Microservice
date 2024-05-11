package com.urooms.publication.publicationMicroservice.infraestructure.repositories;

import com.urooms.publication.publicationMicroservice.domain.entities.Location;
import org.springframework.data.repository.CrudRepository;

public interface LocationRepository extends CrudRepository<Location, Integer>{

}
