package com.ratz.blog.DTO;


import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class PostDTO {

  private Long id;

  @NotEmpty
  @Size(min = 2, message = "Title must have 2 characters")
  private String title;

  @NotEmpty
  @Size(min = 2, message = "Title must have 12 characters")
  private String description;

  @NotEmpty
  private String content;

  private Set<CommentDTO> comments;
}
