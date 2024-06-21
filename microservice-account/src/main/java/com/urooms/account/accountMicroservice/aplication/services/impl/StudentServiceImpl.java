package com.urooms.account.accountMicroservice.aplication.services.impl;

import com.urooms.account.accountMicroservice.aplication.dto.request.StudentRequestDTO;
import com.urooms.account.accountMicroservice.aplication.dto.response.AccountResponseDTO;
import com.urooms.account.accountMicroservice.aplication.dto.response.StudentClientResponseDTO;
import com.urooms.account.accountMicroservice.aplication.dto.response.StudentResponseDTO;
import com.urooms.account.accountMicroservice.aplication.services.StudentService;
import com.urooms.account.accountMicroservice.domain.entities.Student;
import com.urooms.account.accountMicroservice.domain.entities.University;
import com.urooms.account.accountMicroservice.infraestructure.repositories.AccountRepository;
import com.urooms.account.accountMicroservice.infraestructure.repositories.CareerRepository;
import com.urooms.account.accountMicroservice.infraestructure.repositories.StudentRepository;
import com.urooms.account.accountMicroservice.infraestructure.repositories.UniversityRepository;
import com.urooms.account.shared.model.dto.response.ApiResponse;
import com.urooms.account.shared.model.enums.Estatus;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    private final UniversityRepository universityRepository;
    private final CareerRepository careerRepository;
    private final AccountRepository accountRepository;
    private final String REAL_NAME = "urooms";
    private final Keycloak keycloak;

    public StudentServiceImpl(StudentRepository studentRepository, ModelMapper modelMapper, UniversityRepository universityRepository, CareerRepository careerRepository, AccountRepository accountRepository, Keycloak keycloak) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
        this.universityRepository = universityRepository;
        this.careerRepository = careerRepository;
        this.accountRepository = accountRepository;
        this.keycloak = keycloak;
    }

    @Override
    public ApiResponse<StudentClientResponseDTO> getStudentClientById(int id) {
        StudentResponseDTO studentResponseDTO = getStudentById(id).getData();

        if (studentResponseDTO != null) {
            StudentClientResponseDTO studentClientResponseDTO =
                    StudentClientResponseDTO.builder()
                            .id(studentResponseDTO.getId())
                            .gender(studentResponseDTO.getGender())
                            .dni(studentResponseDTO.getDni())
                            .phone(studentResponseDTO.getPhone())
                            .photoUrl(studentResponseDTO.getPhotoUrl()).build();

            return new ApiResponse<>("Student fetched successfully", Estatus.SUCCESS, studentClientResponseDTO);
        } else {
            return new ApiResponse<>("Student not found", Estatus.ERROR, null);
        }
    }

    @Override
    public ApiResponse<List<StudentResponseDTO>> getAllStudents() {
        List<Student> studentList = (List<Student>) studentRepository.findAll();
        List<StudentResponseDTO> studentDTOList = studentList.stream()
                .map(entity ->{
                    StudentResponseDTO studentResponseDTO = modelMapper.map(entity, StudentResponseDTO.class);

                    UserRepresentation userRepresentation = keycloak.realm(REAL_NAME)
                            .users()
                            .get(entity.getAccount())
                            .toRepresentation();

                    AccountResponseDTO accountResponseDTO = modelMapper.map(userRepresentation, AccountResponseDTO.class);

                    studentResponseDTO.setAccount(accountResponseDTO);

                    return studentResponseDTO;
                })
                .collect(Collectors.toList());

        return new ApiResponse<>("All students fetched successfully", Estatus.SUCCESS, studentDTOList);
    }

    @Override
    public ApiResponse<StudentResponseDTO> getStudentById(int id) {
        Optional<Student> studentOptional = studentRepository.findById(id);

        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();

            UserRepresentation userRepresentation = keycloak.realm(REAL_NAME)
                    .users()
                    .get(student.getAccount())
                    .toRepresentation();

            AccountResponseDTO accountResponseDTO = modelMapper.map(userRepresentation, AccountResponseDTO.class);

            StudentResponseDTO responseDTO = modelMapper.map(student, StudentResponseDTO.class);

            responseDTO.setAccount(accountResponseDTO);

            return new ApiResponse<>("Student fetched successfully", Estatus.SUCCESS, responseDTO);
        } else {
            return new ApiResponse<>("Student not found", Estatus.ERROR, null);
        }
    }

    @Override
    public ApiResponse<StudentResponseDTO> createStudent(StudentRequestDTO studentRequestDTO) {

        try {
            // Verifica que el accountId no sea nulo
            String accountId = studentRequestDTO.getAccount();
            if (accountId == null || accountId.isEmpty()) {
                throw new IllegalArgumentException("Account ID is null or empty for the provided student request.");
            }

            // Consulta Keycloak para obtener los detalles de la cuenta
            UserRepresentation userRepresentation = keycloak.realm(REAL_NAME)
                    .users()
                    .get(accountId)
                    .toRepresentation();

            if (userRepresentation == null) {
                return new ApiResponse<>("Account not found", Estatus.ERROR, null);
            }

            // Mapea UserRepresentation a AccountResponseDTO
            AccountResponseDTO accountResponseDTO = modelMapper.map(userRepresentation, AccountResponseDTO.class);

            // Mapea StudentRequestDTO a Student
            Student student = modelMapper.map(studentRequestDTO, Student.class);

            // Asigna las relaciones de universidad y carrera
            student.setUniversity(universityRepository.getUniversitiesById(studentRequestDTO.getUniversity()));
            student.setCareer(careerRepository.getCareerById(studentRequestDTO.getCareer()));

            // Guarda el estudiante en la base de datos
            studentRepository.save(student);

            // Mapea Student a StudentResponseDTO y asigna el AccountResponseDTO
            StudentResponseDTO response = modelMapper.map(student, StudentResponseDTO.class);
            response.setAccount(accountResponseDTO);

            return new ApiResponse<>("Student created successfully", Estatus.SUCCESS, response);

        } catch (Exception e) {
            // Maneja excepciones y registra el error
            System.err.println("Error creating student: " + e.getMessage());
            e.printStackTrace();
            return new ApiResponse<>("Failed to create student", Estatus.ERROR, null);
        }


    }

    @Override
    public ApiResponse<StudentResponseDTO> updateStudent(int id, StudentRequestDTO studentRequestDTO) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isEmpty()) {
            return new ApiResponse<>("Student not found", Estatus.ERROR, null);
        } else {

            UserRepresentation userRepresentation = keycloak.realm(REAL_NAME)
                    .users()
                    .get(studentRequestDTO.getAccount())
                    .toRepresentation();
            AccountResponseDTO accountResponseDTO = modelMapper.map(userRepresentation, AccountResponseDTO.class);

            Student student = studentOptional.get();
            modelMapper.map(studentRequestDTO, student);
            student.setUniversity(universityRepository.getUniversitiesById(studentRequestDTO.getUniversity()));
            student.setCareer(careerRepository.getCareerById(studentRequestDTO.getCareer()));

            studentRepository.save(student);

            StudentResponseDTO response = modelMapper.map(student, StudentResponseDTO.class);

            response.setAccount(accountResponseDTO);

            return new ApiResponse<>("Student updated successfully", Estatus.SUCCESS, response);
        }
    }

    @Override
    public ApiResponse<Void> deleteStudent(int id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isEmpty()) {
            return new ApiResponse<>("Student not found", Estatus.ERROR, null);
        } else {
            studentRepository.deleteById(id);
            return new ApiResponse<>("Student deleted successfully", Estatus.SUCCESS, null);
        }
    }

}
