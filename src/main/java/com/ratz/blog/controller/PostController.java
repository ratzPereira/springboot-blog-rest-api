package com.ratz.blog.controller;

import com.ratz.blog.DTO.PostDTO;
import com.ratz.blog.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostController {

  private final PostService service;

  @PostMapping
  public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO){

    return new ResponseEntity<>(service.createPost(postDTO), HttpStatus.CREATED);
  }

  @GetMapping
  public List<PostDTO> getAllPosts(){

    return service.getAllPosts();
  }

  @GetMapping("/{id}")
  public ResponseEntity<PostDTO> getPostById(@PathVariable Long id){

    return new ResponseEntity<>(service.getPostById(id), HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<PostDTO> updatePost(@PathVariable Long id, @RequestBody PostDTO postDTO){

    return new ResponseEntity<>(service.updatePost(id, postDTO), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deletePost(@PathVariable Long id){

    service.deletePostById(id);

    return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
  }
}
