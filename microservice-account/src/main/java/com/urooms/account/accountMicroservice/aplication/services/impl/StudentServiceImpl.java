package com.urooms.account.accountMicroservice.aplication.services.impl;

import com.urooms.account.accountMicroservice.aplication.dto.request.StudentRequestDTO;
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

    public StudentServiceImpl(StudentRepository studentRepository, ModelMapper modelMapper, UniversityRepository universityRepository, CareerRepository careerRepository, AccountRepository accountRepository) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
        this.universityRepository = universityRepository;
        this.careerRepository = careerRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public ApiResponse<List<StudentResponseDTO>> getAllStudents() {
        List<University> universityList = (List<University>) universityRepository.findAll();
        List<StudentResponseDTO> studentDTOList = universityList.stream()
                .map(entity -> modelMapper.map(entity, StudentResponseDTO.class))
                .collect(Collectors.toList());

        return new ApiResponse<>("All students fetched successfully", Estatus.SUCCESS, studentDTOList);
    }

    @Override
    public ApiResponse<StudentResponseDTO> getStudentById(int id) {
        Optional<Student> studentOptional = studentRepository.findById(id);

        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            StudentResponseDTO responseDTO = modelMapper.map(student, StudentResponseDTO.class);
            return new ApiResponse<>("Student fetched successfully", Estatus.SUCCESS, responseDTO);
        } else {
            return new ApiResponse<>("Student not found", Estatus.ERROR, null);
        }
    }

    @Override
    public ApiResponse<StudentResponseDTO> createStudent(StudentRequestDTO studentRequestDTO) {
        var student = modelMapper.map(studentRequestDTO, Student.class);
        student.setUniversity(universityRepository.getUniversitiesById(studentRequestDTO.getUniversity()));
        student.setCareer(careerRepository.getCareerById(studentRequestDTO.getCareer()));
        student.setAccount(accountRepository.getAccountById(studentRequestDTO.getAccount()));
        studentRepository.save(student);
        var response = modelMapper.map(student, StudentResponseDTO.class);
        return new ApiResponse<>("Student created successfully", Estatus.SUCCESS, response);
    }

    @Override
    public ApiResponse<StudentResponseDTO> updateStudent(int id, StudentRequestDTO studentRequestDTO) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isEmpty()) {
            return new ApiResponse<>("Student not found", Estatus.ERROR, null);
        } else {
            Student student = studentOptional.get();
            modelMapper.map(studentRequestDTO, student);
            student.setUniversity(universityRepository.getUniversitiesById(studentRequestDTO.getUniversity()));
            student.setCareer(careerRepository.getCareerById(studentRequestDTO.getCareer()));
            student.setAccount(accountRepository.getAccountById(studentRequestDTO.getAccount()));
            studentRepository.save(student);
            StudentResponseDTO response = modelMapper.map(student, StudentResponseDTO.class);
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
