package com.ratz.blog.service;

import com.ratz.blog.DTO.PostDTO;
import com.ratz.blog.DTO.PostResponse;



public interface PostService {

  PostDTO createPost(PostDTO postDTO);
  PostResponse getAllPosts(int pageNumber, int pageSize, String sort, String sortDir);
  PostDTO getPostById(Long id);
  PostDTO updatePost(Long id, PostDTO postDTO);
  void deletePostById(Long id);
}
