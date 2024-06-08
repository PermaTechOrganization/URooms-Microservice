package com.urooms.account.accountMicroservice.aplication.controller;

import com.urooms.account.accountMicroservice.aplication.dto.request.AccountRequestDTO;
import com.urooms.account.accountMicroservice.aplication.dto.response.AccountResponseDTO;
import com.urooms.account.accountMicroservice.aplication.services.AccountService;
import com.urooms.account.shared.model.dto.response.ApiResponse;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@Tag(name = "Account", description = "Account API")
@RestController
@RequestMapping("/api/v1/URooms")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    //@Operation(summary = "update an existing account")
    @PutMapping("/accounts/{id}")
    public ResponseEntity<ApiResponse<Object>> updateAccount(@PathVariable String id, @RequestBody AccountRequestDTO accountRequestDTO) {
        var res = accountService.updateAccount(id, accountRequestDTO);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    //@Operation(summary = "delete an account")
    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteAccount(@PathVariable String id) {
        var res = accountService.deleteAccount(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
