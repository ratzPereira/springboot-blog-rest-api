package com.ratz.blog.service.impl;

import com.ratz.blog.DTO.PostDTO;
import com.ratz.blog.DTO.PostResponse;
import com.ratz.blog.entity.Post;
import com.ratz.blog.exception.ResourceNotFoundException;
import com.ratz.blog.repository.PostRepository;
import com.ratz.blog.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
  public PostResponse getAllPosts(int pageNumber, int pageSize, String sortBy, String sortDir) {

    Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

    //create pageable instance
    Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
    Page<Post> list = repository.findAll(pageable);

    //get content for page object
    List<Post> postList = list.getContent();


    List<PostDTO> postDTOS = postList.stream().map(this::mapToPostDTO).collect(Collectors.toList());

    return createPostResponse(list, pageNumber,pageSize, postDTOS);
  }

  @Override
  public PostDTO getPostById(Long id) {

    Post post = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id.toString()));
    return mapToPostDTO(post);
  }


  @Override
  public PostDTO updatePost(Long id, PostDTO postDTO) {
    Post post = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Update Post", "id", id.toString()));

    post.setContent(postDTO.getContent());
    post.setDescription(postDTO.getDescription());
    post.setTitle(postDTO.getTitle());

    repository.save(post);
    return mapToPostDTO(post);
  }


  @Override
  public void deletePostById(Long id) {

    if (repository.findById(id).isPresent()) {
      repository.deleteById(id);
    } else {
      throw new ResourceNotFoundException("Delete Post", "ID:", id.toString());
    }

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

  private PostResponse createPostResponse(Page postResponse, int pageNumber, int pageSize, List<PostDTO> postDTOS) {

    return PostResponse.builder()
        .content(postDTOS)
        .isLast(postResponse.isLast())
        .pageNumber(pageNumber)
        .pageSize(pageSize)
        .totalElements(postResponse.getTotalElements())
        .totalPages(postResponse.getTotalPages())
        .build();
  }
}
