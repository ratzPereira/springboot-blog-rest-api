package com.ratz.blog.controller;

import com.ratz.blog.DTO.PostDTO;
import com.ratz.blog.DTO.PostResponse;
import com.ratz.blog.service.PostService;
import com.ratz.blog.utils.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Api(value = "CRUD Rest Api Post resources")
@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostController {

  private final PostService service;

  @ApiOperation(value = "Creat Post REST API")
  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping
  public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDTO){

    return new ResponseEntity<>(service.createPost(postDTO), HttpStatus.CREATED);
  }

  @ApiOperation(value = "Get All Posts REST API")
  @GetMapping
  public PostResponse getAllPosts(
      @RequestParam(value = "pageNumber", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNumber,
      @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
      @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sort,
      @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
  ){

    return service.getAllPosts(pageNumber,pageSize, sort, sortDir);
  }

  @ApiOperation(value = "Get Post by ID REST API")
  @GetMapping("/{id}")
  public ResponseEntity<PostDTO> getPostById(@PathVariable Long id){

    return new ResponseEntity<>(service.getPostById(id), HttpStatus.OK);
  }

  @ApiOperation(value = "Update Post REST API")
  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping("/{id}")
  public ResponseEntity<PostDTO> updatePost(@PathVariable Long id, @Valid @RequestBody PostDTO postDTO){

    return new ResponseEntity<>(service.updatePost(id, postDTO), HttpStatus.OK);
  }

  @ApiOperation(value = "Delete Post by ID REST API")
  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("/{id}")
  public ResponseEntity<String> deletePost(@PathVariable Long id){

    service.deletePostById(id);

    return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
  }
}
