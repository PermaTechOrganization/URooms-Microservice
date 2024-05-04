package com.urooms.account.accountMicroservice.aplication.services;

import com.urooms.account.accountMicroservice.aplication.dto.request.AccountRequestDTO;
import com.urooms.account.accountMicroservice.aplication.dto.response.AccountResponseDTO;
import com.urooms.account.accountMicroservice.domain.entities.Account;
import com.urooms.account.shared.model.dto.response.ApiResponse;

public interface AccountService {

    Account getAccountById (int id);

    ApiResponse<AccountResponseDTO> createAccount(AccountRequestDTO accountRequestDTO);

    ApiResponse<AccountResponseDTO> updateAccount(int id, AccountRequestDTO accountRequestDTO);

    ApiResponse<Void> deleteAccount(int id);

}
