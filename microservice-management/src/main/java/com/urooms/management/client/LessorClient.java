package com.urooms.management.client;

import com.urooms.management.managementMicroservice.application.dto.response.LessorResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-account", url = "http://localhost:8080/api/v1/URooms")
public interface LessorClient {

    @GetMapping("/lessorsClient/{id}")
    LessorResponseDTO getLessorById(@PathVariable("id") int id);

}
