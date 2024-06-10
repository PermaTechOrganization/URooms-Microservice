package com.urooms.account.accountMicroservice.infraestructure.repositories;

import com.urooms.account.accountMicroservice.domain.entities.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Integer>{

    Account getAccountById (int id);

}
