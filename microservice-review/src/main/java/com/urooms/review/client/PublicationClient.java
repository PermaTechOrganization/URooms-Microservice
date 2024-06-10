package com.urooms.review.client;

import com.urooms.review.reviewMicroservice.application.dto.response.PublicationResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-publication", url = "http://localhost:8080/api/v2/URooms")
public interface PublicationClient {

    @GetMapping("/publicationsClient/{id}")
    PublicationResponseDTO getPublicationById(@PathVariable("id") int id);

}
