package com.example.cs4550finalprojectjava.repositories;

import com.example.cs4550finalprojectjava.models.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Integer> {

}
