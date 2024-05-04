package com.urooms.account.accountMicroservice.aplication.services;

import com.urooms.account.accountMicroservice.aplication.dto.request.RoleRequestDTO;
import com.urooms.account.accountMicroservice.aplication.dto.response.RoleResponseDTO;
import com.urooms.account.accountMicroservice.domain.entities.Role;
import com.urooms.account.shared.model.dto.response.ApiResponse;

public interface RoleService {

    Role getRoleById(int id);

    ApiResponse<RoleResponseDTO> createRole(RoleRequestDTO roleRequestDTO);

    ApiResponse<RoleResponseDTO> updateRole(int id, RoleRequestDTO roleRequestDTO);

    ApiResponse<Void> deleteRole(int id);

    boolean existRole(int id);

}
