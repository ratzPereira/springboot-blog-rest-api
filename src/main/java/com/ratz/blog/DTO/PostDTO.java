package com.ratz.blog.DTO;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@ApiModel(value = "Post model information")
@Data
public class PostDTO {

  @ApiModelProperty(value = "Blog Post ID")
  private Long id;

  @ApiModelProperty(value = "Blog Post Title")
  @NotEmpty
  @Size(min = 2, message = "Title must have 2 characters")
  private String title;

  @ApiModelProperty(value = "Blog Post description")
  @NotEmpty
  @Size(min = 2, message = "Title must have 12 characters")
  private String description;

  @ApiModelProperty(value = "Blog Post content")
  @NotEmpty
  private String content;

  @ApiModelProperty(value = "Blog Post Comments")
  private Set<CommentDTO> comments;
}
