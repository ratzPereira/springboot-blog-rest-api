package com.ratz.blog.service;

import com.ratz.blog.DTO.PostDTO;
import com.ratz.blog.entity.Post;

import java.util.List;


public interface PostService {

  PostDTO createPost(PostDTO postDTO);
  List<PostDTO> getAllPosts(int pageNumber, int pageSize);
  PostDTO getPostById(Long id);
  PostDTO updatePost(Long id, PostDTO postDTO);
  void deletePostById(Long id);
}
