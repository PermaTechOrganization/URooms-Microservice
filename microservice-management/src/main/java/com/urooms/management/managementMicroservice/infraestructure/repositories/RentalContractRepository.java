package com.urooms.management.managementMicroservice.infraestructure.repositories;

import com.urooms.management.managementMicroservice.domain.entities.RentalContract;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RentalContractRepository extends CrudRepository<RentalContract, Integer> {

    RentalContract getRentalContractById(int id);

    List<RentalContract> getRentalContractByLessor(int lessor);

    List<RentalContract> getRentalContractByStudent(int student);

}
