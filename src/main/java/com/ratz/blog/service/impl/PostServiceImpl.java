package com.ratz.blog.service.impl;

import com.ratz.blog.DTO.PostDTO;
import com.ratz.blog.entity.Post;
import com.ratz.blog.repository.PostRepository;
import com.ratz.blog.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {


  private PostRepository repository;

  @Override
  public PostDTO createPost(PostDTO postDTO) {

    Post post = mapToPostEntity(postDTO);

    repository.save(post);

    PostDTO dto = mapToPostDTO(post);

    return dto;
  }

  @Override
  public List<PostDTO> getAllPosts() {

    List<Post> list = repository.findAll();

    List<PostDTO> dtoList = list.stream().map(this::mapToPostDTO).collect(Collectors.toList());

    return dtoList;
  }

  private PostDTO mapToPostDTO(Post post) {

    return PostDTO.builder()
        .id(post.getId())
        .content(post.getContent())
        .title(post.getTitle())
        .description(post.getDescription())
        .build();


  }

  private Post mapToPostEntity(PostDTO postDTO) {

    return Post.builder()
        .id(postDTO.getId())
        .content(postDTO.getContent())
        .description(postDTO.getDescription())
        .title(postDTO.getTitle())
        .build();
  }
}
