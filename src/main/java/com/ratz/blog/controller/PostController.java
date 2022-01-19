package com.ratz.blog.controller;

import com.ratz.blog.DTO.PostDTO;
import com.ratz.blog.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostController {

  private final PostService service;

  @PostMapping
  public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO){

    return new ResponseEntity<>(service.createPost(postDTO), HttpStatus.CREATED);
  }
}
