package com.ratz.blog.service;

import com.ratz.blog.DTO.PostDTO;

import java.util.List;


public interface PostService {

  PostDTO createPost(PostDTO postDTO);
  List<PostDTO> getAllPosts();
  PostDTO getPostById(Long id);
}
