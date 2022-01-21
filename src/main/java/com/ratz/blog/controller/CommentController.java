package com.ratz.blog.controller;

import com.ratz.blog.DTO.CommentDTO;
import com.ratz.blog.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
@AllArgsConstructor
public class CommentController {

  private CommentService service;

  @PostMapping("posts/{id}/comments")
  public ResponseEntity<CommentDTO> createComment(@PathVariable Long id, @RequestBody CommentDTO commentDTO){

    return new ResponseEntity(service.createComment(id,commentDTO), HttpStatus.CREATED);
  }

}
