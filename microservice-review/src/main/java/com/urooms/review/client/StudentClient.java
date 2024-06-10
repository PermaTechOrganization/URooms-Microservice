package com.urooms.review.client;

import com.urooms.review.reviewMicroservice.application.dto.response.StudentResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-account", url = "http://localhost:8080/api/v1/URooms")
public interface StudentClient {

    @GetMapping("/studentsClient/{id}")
    StudentResponseDTO getStudentById(@PathVariable("id") int id);

}
