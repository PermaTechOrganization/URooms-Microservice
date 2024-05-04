package com.urooms.account.accountMicroservice.aplication.controller;

import com.urooms.account.accountMicroservice.aplication.dto.request.StudentRequestDTO;
import com.urooms.account.accountMicroservice.aplication.dto.response.StudentResponseDTO;
import com.urooms.account.accountMicroservice.aplication.services.StudentService;
import com.urooms.account.shared.model.dto.response.ApiResponse;
import com.urooms.account.shared.model.enums.Estatus;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Tag(name = "University", description = "University API")
@RestController
@RequestMapping("/api/v1/URooms")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    //@Operation(summary = "get all students")
    @GetMapping("/students")
    public ResponseEntity<ApiResponse<List<StudentResponseDTO>>> getStudents() {
        var res = studentService.getAllStudents();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    //@Operation(summary = "get student by id")
    @GetMapping("/students/{id}")
    public ResponseEntity<ApiResponse<StudentResponseDTO>> getStudentById(@PathVariable("id") int id){
        ApiResponse<StudentResponseDTO> response = studentService.getStudentById(id);
        return new ResponseEntity<>(response, response.getStatus() == Estatus.SUCCESS ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    //@Operation(summary = "create a new student")
    @PostMapping("/students")
    public ResponseEntity<ApiResponse<StudentResponseDTO>> createStudent(@RequestBody StudentRequestDTO studentRequestDTO) {
        var res = studentService.createStudent(studentRequestDTO);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    //@Operation(summary = "update an existing student")
    @PutMapping("/students/{id}")
    public ResponseEntity<ApiResponse<StudentResponseDTO>> updateStudent(@PathVariable int id, @RequestBody StudentRequestDTO studentRequestDTO) {
        var res = studentService.updateStudent(id, studentRequestDTO);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    //@Operation(summary = "delete a student")
    @DeleteMapping("/students/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteStudent(@PathVariable("id") int id) {
        var res = studentService.deleteStudent(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
