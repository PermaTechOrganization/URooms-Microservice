package com.urooms.account.accountMicroservice.infraestructure.repositories;

import com.urooms.account.accountMicroservice.domain.entities.Career;
import org.springframework.data.repository.CrudRepository;

public interface CareerRepository extends CrudRepository<Career, Integer> {

    Career getCareerById (int id);

}
