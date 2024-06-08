package com.urooms.account.accountMicroservice.domain.mappers;

import com.urooms.account.accountMicroservice.aplication.dto.response.AccountResponseDTO;
import org.modelmapper.Converter;
import org.keycloak.representations.idm.UserRepresentation;

public class KeycloakMapper {

    public KeycloakMapper() { }

    public static Converter<UserRepresentation, AccountResponseDTO> userRepresentationAccountResponseDTO(){
        return (context) ->{
            var dto = new AccountResponseDTO();
            var source = context.getSource();

            dto.setId(source.getId());
            dto.setUsername(source.getUsername());
            dto.setFirstName(source.getFirstName());
            dto.setLastName(source.getLastName());
            dto.setEmail(source.getEmail());
            return dto;
        };
    }

}
