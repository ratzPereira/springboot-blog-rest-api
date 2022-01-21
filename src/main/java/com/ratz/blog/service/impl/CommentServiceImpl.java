package com.ratz.blog.service.impl;

import com.ratz.blog.DTO.CommentDTO;
import com.ratz.blog.entity.Comment;
import com.ratz.blog.entity.Post;
import com.ratz.blog.exception.ResourceNotFoundException;
import com.ratz.blog.repository.CommentRepository;
import com.ratz.blog.repository.PostRepository;
import com.ratz.blog.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

  private CommentRepository commentRepository;
  private PostRepository postRepository;

  @Override
  public CommentDTO createComment(Long id, CommentDTO commentDTO) {

    Comment comment = Comment.builder()
        .id(commentDTO.getId())
        .name(commentDTO.getName())
        .body(commentDTO.getBody())
        .email(commentDTO.getEmail())
        .build();

    Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Get Post", "id: " , id.toString()));

    comment.setPost(post);
    commentRepository.save(comment);

    CommentDTO dto = CommentDTO.builder()
        .body(comment.getBody())
        .email(comment.getEmail())
        .id(comment.getId())
        .name(comment.getName())
        .build();

    return dto;
  }
}
