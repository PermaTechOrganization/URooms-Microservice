package com.urooms.review.reviewMicroservice.infraestructure.repositories;

import com.urooms.review.reviewMicroservice.domain.entities.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Integer> {

    Comment getCommentById(int id);

    List<Comment> getCommentByPublication(int publicationId);

    List<Comment> getCommentByStudent(int studentId);

}
