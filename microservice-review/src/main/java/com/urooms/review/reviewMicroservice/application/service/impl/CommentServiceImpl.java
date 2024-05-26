package com.urooms.review.reviewMicroservice.application.service.impl;

import com.urooms.review.client.PublicationClient;
import com.urooms.review.client.StudentClient;
import com.urooms.review.reviewMicroservice.application.dto.request.CommentRequestDTO;
import com.urooms.review.reviewMicroservice.application.dto.response.CommentResponseDTO;
import com.urooms.review.reviewMicroservice.application.service.CommentService;
import com.urooms.review.reviewMicroservice.domain.entities.Comment;
import com.urooms.review.reviewMicroservice.infraestructure.repositories.CommentRepository;
import com.urooms.review.shared.model.dto.response.ApiResponse;
import com.urooms.review.shared.model.enums.Estatus;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final ModelMapper modelMapper;
    private final CommentRepository commentRepository;
    private final PublicationClient publicationClient;
    private final StudentClient studentClient;

    public CommentServiceImpl(ModelMapper modelMapper, CommentRepository commentRepository, PublicationClient publicationClient, StudentClient studentClient) {
        this.modelMapper = modelMapper;
        this.commentRepository = commentRepository;
        this.publicationClient = publicationClient;
        this.studentClient = studentClient;
    }

    @Override
    public ApiResponse<CommentResponseDTO> getCommentById(int id) {
        Optional<Comment> commentOptional = commentRepository.findById(id);
        if(commentOptional.isPresent()){
            Comment comment = commentOptional.get();
            CommentResponseDTO responseDTO =
                    CommentResponseDTO.builder()
                            .id(comment.getId())
                            .comment(comment.getComment())
                            .rating(comment.getRating())
                            .student(studentClient.getStudentById(comment.getStudent()))
                            .publication(publicationClient.getPublicationById(comment.getPublication()))
                            .build();
            return new ApiResponse<>("Comment fetched successfully", Estatus.SUCCESS,responseDTO);
        } else {
            return new ApiResponse<>("Comment not found", Estatus.ERROR,null);
        }

    }

    @Override
    public ApiResponse<CommentResponseDTO> createComment(CommentRequestDTO commentRequestDTO) {
        var comment = modelMapper.map(commentRequestDTO, Comment.class);
        comment.setStudent(commentRequestDTO.getStudent());
        comment.setPublication(commentRequestDTO.getPublication());

        if(publicationClient.getPublicationById(comment.getPublication()) == null || studentClient.getStudentById(comment.getStudent()) == null){
            return new ApiResponse<>("Publication or Student not found", Estatus.ERROR, null);
        }else {
            commentRepository.save(comment);
            CommentResponseDTO responseDTO = modelMapper.map(comment, CommentResponseDTO.class);
            responseDTO.setStudent(studentClient.getStudentById(comment.getStudent()));
            responseDTO.setPublication(publicationClient.getPublicationById(comment.getPublication()));

            return new ApiResponse<>("Comment created successfully", Estatus.SUCCESS, responseDTO);
        }
    }

    @Override
    public ApiResponse<Void> deleteComment(int id) {
        Optional<Comment> commentOptional = commentRepository.findById(id);
        if(commentOptional.isPresent()){
            commentRepository.deleteById(id);
            return new ApiResponse<>("Comment deleted successfully", Estatus.SUCCESS, null);
        } else {
            return new ApiResponse<>("Comment not found", Estatus.ERROR, null);
        }
    }

    @Override
    public ApiResponse<CommentResponseDTO> updateComment(int id, CommentRequestDTO commentRequestDTO) {
        Optional<Comment> commentOptional = commentRepository.findById(id);

        if(commentOptional.isEmpty()){
            return new ApiResponse<>("Comment not found", Estatus.ERROR, null);
        }else{
            Comment comment = commentOptional.get();
            modelMapper.map(commentRequestDTO, comment);
            comment.setPublication(commentRequestDTO.getPublication());
            comment.setStudent(commentRequestDTO.getStudent());
            commentRepository.save(comment);
            CommentResponseDTO responseDTO = modelMapper.map(comment, CommentResponseDTO.class);
            responseDTO.setStudent(studentClient.getStudentById(comment.getStudent()));
            responseDTO.setPublication(publicationClient.getPublicationById(comment.getPublication()));
            return new ApiResponse<>("Comment updated successfully", Estatus.SUCCESS, responseDTO);
        }
    }

    @Override
    public ApiResponse<List<CommentResponseDTO>> getCommentsByPublicationId(int publicationId) {
        List<Comment> comments = commentRepository.getCommentByPublication(publicationId);
        List<CommentResponseDTO> responseDTO = comments.stream()
                .map(comment ->{
                    CommentResponseDTO commentResponseDTO = modelMapper.map(comment, CommentResponseDTO.class);
                    commentResponseDTO.setStudent(studentClient.getStudentById(comment.getStudent()));
                    commentResponseDTO.setPublication(publicationClient.getPublicationById(comment.getPublication()));
                    return commentResponseDTO;
                })
                .toList();
        return new ApiResponse<>("Comments fetched successfully", Estatus.SUCCESS, responseDTO);
    }

    @Override
    public ApiResponse<List<CommentResponseDTO>> getCommentsByStudentId(int studentId) {
        List<Comment> comments = commentRepository.getCommentByStudent(studentId);
        List<CommentResponseDTO> responseDTO = comments.stream()
                .map(comment ->{
                    CommentResponseDTO commentResponseDTO = modelMapper.map(comment, CommentResponseDTO.class);
                    commentResponseDTO.setStudent(studentClient.getStudentById(comment.getStudent()));
                    commentResponseDTO.setPublication(publicationClient.getPublicationById(comment.getPublication()));
                    return commentResponseDTO;
                })
                .toList();
        return new ApiResponse<>("Comments fetched successfully", Estatus.SUCCESS, responseDTO);
    }

}
