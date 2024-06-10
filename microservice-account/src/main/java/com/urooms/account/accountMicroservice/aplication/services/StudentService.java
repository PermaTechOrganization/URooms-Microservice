package com.urooms.account.accountMicroservice.aplication.services;

import com.urooms.account.accountMicroservice.aplication.dto.request.StudentRequestDTO;
import com.urooms.account.accountMicroservice.aplication.dto.response.StudentClientResponseDTO;
import com.urooms.account.accountMicroservice.aplication.dto.response.StudentResponseDTO;
import com.urooms.account.shared.model.dto.response.ApiResponse;

import java.util.List;

public interface StudentService {

    ApiResponse<List<StudentResponseDTO>> getAllStudents();

    ApiResponse<StudentResponseDTO> getStudentById(int id);

    ApiResponse<StudentClientResponseDTO> getStudentClientById(int id);

    ApiResponse<Void> deleteStudent(int id);

    ApiResponse<StudentResponseDTO> createStudent(StudentRequestDTO studentRequestDTO);

    ApiResponse<StudentResponseDTO> updateStudent(int id, StudentRequestDTO studentRequestDTO);
}
