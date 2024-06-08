package com.urooms.account.shared.config;

import com.urooms.account.accountMicroservice.aplication.dto.response.AccountResponseDTO;
import com.urooms.account.accountMicroservice.domain.mappers.KeycloakMapper;
import org.keycloak.representations.idm.UserRepresentation;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

        modelMapper.createTypeMap(UserRepresentation.class, AccountResponseDTO.class)
                .setConverter(KeycloakMapper.userRepresentationAccountResponseDTO());

        return modelMapper;
    }
}
