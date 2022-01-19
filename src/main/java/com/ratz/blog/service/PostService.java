package com.ratz.blog.service;

import com.ratz.blog.DTO.PostDTO;
import com.ratz.blog.repository.PostRepository;

public interface PostService {

  PostDTO createPost(PostDTO postDTO);
}
