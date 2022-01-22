package com.ratz.blog.service.impl;

import com.ratz.blog.DTO.CommentDTO;
import com.ratz.blog.entity.Comment;
import com.ratz.blog.entity.Post;
import com.ratz.blog.exception.BlogAPIException;
import com.ratz.blog.exception.ResourceNotFoundException;
import com.ratz.blog.repository.CommentRepository;
import com.ratz.blog.repository.PostRepository;
import com.ratz.blog.service.CommentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {

  private CommentRepository commentRepository;
  private PostRepository postRepository;

  @Override
  public CommentDTO createComment(Long id, CommentDTO commentDTO) {

    Comment comment = Comment.builder()
        .id(commentDTO.getId())
        .name(commentDTO.getName())
        .body(commentDTO.getBody())
        .email(commentDTO.getEmail())
        .build();

    Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Get Post", "id: ", id.toString()));

    comment.setPost(post);
    commentRepository.save(comment);

    CommentDTO dto = entityToCommentDTO(comment);

    return dto;
  }

  @Override
  public List<CommentDTO> getCommentsByPostId(long id) {

    List<Comment> comments = commentRepository.findByPostId(id);

    return comments.stream().map(this::entityToCommentDTO).collect(Collectors.toList());
  }


  @Override
  public CommentDTO getCommentById(Long postId, Long commentId) {

    Post post = postRepository.findById(postId).orElseThrow(() ->
        new ResourceNotFoundException("Post", "Id: ", postId.toString()));

    Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
        new ResourceNotFoundException("Comment", "Id: ", commentId.toString()));


    if (!comment.getPost().getId().equals(post.getId())) {
      log.info("The comment id does not exist on this post");
      throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
    }


    return entityToCommentDTO(comment);
  }


  @Override
  public CommentDTO updateComment(Long postId, Long commentId, CommentDTO commentDTO) {
    Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
        new ResourceNotFoundException("Comment", "Id: ", commentId.toString()));

    Post post = postRepository.findById(postId).orElseThrow(() ->
        new ResourceNotFoundException("Post", "Id: ", postId.toString()));

    if (!comment.getPost().getId().equals(post.getId())) {
      log.info("The comment id does not exist on this post");
      throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
    }

    comment.setBody(commentDTO.getBody());
    comment.setEmail(commentDTO.getEmail());
    comment.setName(commentDTO.getName());

    commentRepository.save(comment);

    return entityToCommentDTO(comment);
  }

  private CommentDTO entityToCommentDTO(Comment comment) {

    return CommentDTO.builder()
        .body(comment.getBody())
        .email(comment.getEmail())
        .id(comment.getId())
        .name(comment.getName())
        .build();
  }
}
