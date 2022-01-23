package com.ratz.blog.controller;

import com.ratz.blog.DTO.CommentDTO;
import com.ratz.blog.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/")
@AllArgsConstructor
public class CommentController {

  private CommentService service;

  @PostMapping("posts/{id}/comments")
  public ResponseEntity<CommentDTO> createComment(@PathVariable Long id, @Valid @RequestBody CommentDTO commentDTO) {

    return new ResponseEntity(service.createComment(id, commentDTO), HttpStatus.CREATED);
  }

  @GetMapping("posts/{id}/comments")
  public List<CommentDTO> getAllCommentsByPostId(@PathVariable Long id) {

    return service.getCommentsByPostId(id);
  }

  @GetMapping("posts/{postId}/comments/{commentId}")
  public ResponseEntity<CommentDTO> getCommentById(@PathVariable Long postId, @PathVariable Long commentId) {

    return new ResponseEntity<>(service.getCommentById(postId, commentId), HttpStatus.OK);
  }

  @PutMapping("posts/{postId}/comments/{commentId}")
  public ResponseEntity<CommentDTO> updateComment(
      @PathVariable Long postId,
      @PathVariable Long commentId,
      @Valid @RequestBody CommentDTO commentDTO
  ) {

    return new ResponseEntity<>(service.updateComment(postId, commentId, commentDTO), HttpStatus.OK);
  }

  @DeleteMapping("posts/{postId}/comments/{commentId}")
  public ResponseEntity<String> deleteComment(@PathVariable Long postId, @PathVariable Long commentId){

    service.deleteComment(postId, commentId);
    return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
  }

}
