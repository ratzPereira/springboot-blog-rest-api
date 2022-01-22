package com.ratz.blog.service;

import com.ratz.blog.DTO.CommentDTO;
import com.ratz.blog.entity.Comment;

import java.util.List;

public interface CommentService {

  CommentDTO createComment(Long id, CommentDTO commentDTO);
  List<CommentDTO> getCommentsByPostId(long id);
  CommentDTO getCommentById(Long postId, Long commentId);
}
