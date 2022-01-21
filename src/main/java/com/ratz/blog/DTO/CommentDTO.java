package com.ratz.blog.DTO;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class CommentDTO {

  private Long id;
  private String name;
  private String email;
  private String body;
}
