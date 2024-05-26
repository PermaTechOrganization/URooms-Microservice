package com.urooms.review.reviewMicroservice.application.service;

import com.urooms.review.reviewMicroservice.application.dto.request.CommentRequestDTO;
import com.urooms.review.reviewMicroservice.application.dto.response.CommentResponseDTO;
import com.urooms.review.shared.model.dto.response.ApiResponse;

import java.util.List;

public interface CommentService {

    ApiResponse<CommentResponseDTO> getCommentById(int id);

    ApiResponse<Void> deleteComment(int id);

    ApiResponse<CommentResponseDTO> createComment(CommentRequestDTO commentRequestDTO);

    ApiResponse<CommentResponseDTO> updateComment(int id, CommentRequestDTO commentRequestDTO);

    ApiResponse<List<CommentResponseDTO>> getCommentsByPublicationId(int publicationId);

    ApiResponse<List<CommentResponseDTO>> getCommentsByStudentId(int studentId);

}
