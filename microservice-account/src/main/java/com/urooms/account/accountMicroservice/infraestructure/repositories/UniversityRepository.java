package com.urooms.account.accountMicroservice.infraestructure.repositories;

import com.urooms.account.accountMicroservice.domain.entities.University;
import org.springframework.data.repository.CrudRepository;

public interface UniversityRepository extends CrudRepository<University, Integer>{

    University getUniversitiesById(int id);

}
