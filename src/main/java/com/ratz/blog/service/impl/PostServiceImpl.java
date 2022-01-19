package com.ratz.blog.service.impl;

import com.ratz.blog.DTO.PostDTO;
import com.ratz.blog.entity.Post;
import com.ratz.blog.repository.PostRepository;
import com.ratz.blog.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {


  private PostRepository repository;

  @Override
  public PostDTO createPost(PostDTO postDTO) {

    Post post = Post.builder()
        .id(postDTO.getId())
        .content(postDTO.getContent())
        .description(postDTO.getDescription())
        .title(postDTO.getTitle())
        .build();

    repository.save(post);

    PostDTO dto = PostDTO.builder()
        .id(post.getId())
        .content(post.getContent())
        .title(post.getTitle())
        .description(post.getDescription())
        .build();

    return dto;
  }
}
