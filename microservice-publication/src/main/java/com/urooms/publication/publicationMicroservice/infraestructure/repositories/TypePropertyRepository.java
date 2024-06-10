package com.urooms.publication.publicationMicroservice.infraestructure.repositories;

import com.urooms.publication.publicationMicroservice.domain.entities.TypeProperty;
import org.springframework.data.repository.CrudRepository;

public interface TypePropertyRepository extends CrudRepository<TypeProperty, Integer>{

    TypeProperty getTypePropertyById(int id);

}
