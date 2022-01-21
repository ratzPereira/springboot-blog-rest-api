package com.ratz.blog.service;

import com.ratz.blog.DTO.CommentDTO;

public interface CommentService {

  CommentDTO createComment(Long id, CommentDTO commentDTO);
}
