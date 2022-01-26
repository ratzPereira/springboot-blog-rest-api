package com.ratz.blog.controller;

import com.ratz.blog.DTO.CommentDTO;
import com.ratz.blog.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "CRUD REST API for comments")
@RestController
@RequestMapping("/api/")
@AllArgsConstructor
public class CommentController {

  private CommentService service;

  @ApiOperation(value = "Create comment REST API")
  @PostMapping("posts/{id}/comments")
  public ResponseEntity<CommentDTO> createComment(@PathVariable Long id, @Valid @RequestBody CommentDTO commentDTO) {

    return new ResponseEntity(service.createComment(id, commentDTO), HttpStatus.CREATED);
  }

  @ApiOperation(value = "Get All comments by Post ID REST API")
  @GetMapping("posts/{id}/comments")
  public List<CommentDTO> getAllCommentsByPostId(@PathVariable Long id) {

    return service.getCommentsByPostId(id);
  }

  @ApiOperation(value = "Get single comment by ID REST API")
  @GetMapping("posts/{postId}/comments/{commentId}")
  public ResponseEntity<CommentDTO> getCommentById(@PathVariable Long postId, @PathVariable Long commentId) {

    return new ResponseEntity<>(service.getCommentById(postId, commentId), HttpStatus.OK);
  }

  @ApiOperation(value = "Update comment REST API")
  @PutMapping("posts/{postId}/comments/{commentId}")
  public ResponseEntity<CommentDTO> updateComment(
      @PathVariable Long postId,
      @PathVariable Long commentId,
      @Valid @RequestBody CommentDTO commentDTO
  ) {

    return new ResponseEntity<>(service.updateComment(postId, commentId, commentDTO), HttpStatus.OK);
  }

  @ApiOperation(value = "Delete comment REST API")
  @DeleteMapping("posts/{postId}/comments/{commentId}")
  public ResponseEntity<String> deleteComment(@PathVariable Long postId, @PathVariable Long commentId){

    service.deleteComment(postId, commentId);
    return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
  }

}
