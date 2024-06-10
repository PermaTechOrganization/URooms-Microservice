package com.urooms.account.accountMicroservice.aplication.services.impl;

import com.urooms.account.accountMicroservice.aplication.dto.request.AccountRequestDTO;
import com.urooms.account.accountMicroservice.aplication.dto.response.AccountResponseDTO;
import com.urooms.account.accountMicroservice.aplication.services.AccountService;
import com.urooms.account.accountMicroservice.domain.entities.Account;
import com.urooms.account.accountMicroservice.infraestructure.repositories.AccountRepository;
import com.urooms.account.accountMicroservice.infraestructure.repositories.RoleRepository;
import com.urooms.account.shared.exception.CustomException;
import com.urooms.account.shared.exception.ResourceNotFoundException;
import com.urooms.account.shared.model.dto.response.ApiResponse;
import com.urooms.account.shared.model.enums.Estatus;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import org.keycloak.admin.client.Keycloak;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final ModelMapper modelMapper;
    private final Keycloak keycloak;
    private final String REAL_NAME = "urooms";

    public AccountServiceImpl(ModelMapper modelMapper, Keycloak keycloak) {
        this.modelMapper = modelMapper;
        this.keycloak = keycloak;
    }

    @Override
    public ApiResponse<Object> updateAccount(String id, AccountRequestDTO accountRequestDTO) {

        try{
            var existingAccount = keycloak.realm(REAL_NAME)
                    .users()
                    .get(id)
                    .toRepresentation();

            existingAccount.setEmail(accountRequestDTO.getEmail());
            existingAccount.setUsername(accountRequestDTO.getUserName());

            keycloak.realm(REAL_NAME)
                    .users()
                    .get(id)
                    .update(existingAccount);

            return new ApiResponse<>("Account updated successfully", Estatus.SUCCESS, null);
        } catch (NotFoundException e){
            throw  new ResourceNotFoundException("User", "id", id);
        } catch (BadRequestException e){
            throw new CustomException(HttpStatus.BAD_REQUEST, "Error while fetching user details. Please try again later.", e.getMessage());
        }

        /*
        Optional<Account> accountOptional = accountRepository.findById(id);

        if (accountOptional.isEmpty()) {
            return new ApiResponse<>("Account not found", Estatus.ERROR, null);
        }else {
            Account account = accountOptional.get();
            modelMapper.map(accountRequestDTO, account);
            accountRepository.save(account);
            AccountResponseDTO response = modelMapper.map(account, AccountResponseDTO.class);
            return new ApiResponse<>("Account updated successfully", Estatus.SUCCESS, response);
        }*/
    }

    @Override
    public ApiResponse<Object> deleteAccount(String id) {

        try{
            keycloak.realm(REAL_NAME)
                    .users()
                    .get(id)
                    .remove();

            return new ApiResponse<>("Account deleted successfully", Estatus.SUCCESS, null);
        }catch (NotFoundException e){
            throw  new ResourceNotFoundException("User", "id", id);
        }catch (BadRequestException e){
            throw new CustomException(HttpStatus.BAD_REQUEST, "Error while fetching user details. Please try again later.", e.getMessage());
        }

    }

}
