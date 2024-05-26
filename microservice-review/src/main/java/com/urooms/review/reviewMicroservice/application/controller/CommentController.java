package com.urooms.review.reviewMicroservice.application.controller;

import com.urooms.review.reviewMicroservice.application.dto.request.CommentRequestDTO;
import com.urooms.review.reviewMicroservice.application.dto.response.CommentResponseDTO;
import com.urooms.review.reviewMicroservice.application.service.CommentService;
import com.urooms.review.shared.model.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v3/URooms")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<ApiResponse<CommentResponseDTO>> getCommentById(@PathVariable int id) {
        return ResponseEntity.ok(commentService.getCommentById(id));
    }

    @PostMapping("/comments")
    public ResponseEntity<ApiResponse<CommentResponseDTO>> createComment(@RequestBody CommentRequestDTO commentRequestDTO) {
        return ResponseEntity.ok(commentService.createComment(commentRequestDTO));
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteComment(@PathVariable("id") int id) {
        return ResponseEntity.ok(commentService.deleteComment(id));
    }

    @PutMapping("/comments/{id}")
    public ResponseEntity<ApiResponse<CommentResponseDTO>> updateComment(@PathVariable("id") int id, @RequestBody CommentRequestDTO commentRequestDTO) {
        return ResponseEntity.ok(commentService.updateComment(id, commentRequestDTO));
    }

    @GetMapping("/comments/publication/{publicationId}")
    public ResponseEntity<ApiResponse<List<CommentResponseDTO>>> getCommentsByPublicationId(@PathVariable("publicationId") int publicationId) {
        return ResponseEntity.ok(commentService.getCommentsByPublicationId(publicationId));
    }

    @GetMapping("/comments/student/{studentId}")
    public ResponseEntity<ApiResponse<List<CommentResponseDTO>>> getCommentsByStudentId(@PathVariable("studentId") int studentId) {
        return ResponseEntity.ok(commentService.getCommentsByStudentId(studentId));
    }

}
