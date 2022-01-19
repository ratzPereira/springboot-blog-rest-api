package com.ratz.blog.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostDTO {

  private Long id;
  private String title;
  private String description;
  private String content;
}
