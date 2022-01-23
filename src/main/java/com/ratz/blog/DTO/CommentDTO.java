package com.ratz.blog.DTO;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Data
public class CommentDTO {

  private Long id;

  @NotEmpty
  @Size(min = 2, message = "Title must have 2 characters")
  private String name;

  @NotEmpty
  @Email
  private String email;

  @NotEmpty
  @Size(min = 12, message = "Title must have 12 characters")
  private String body;
}
