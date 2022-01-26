package com.ratz.blog.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@ApiModel(value = "Comment model information")
@Data
public class CommentDTO {

  @ApiModelProperty(value = "Blog Comment ID")
  private Long id;

  @ApiModelProperty(value = "Blog Comment name")
  @NotEmpty
  @Size(min = 2, message = "Title must have 2 characters")
  private String name;

  @ApiModelProperty(value = "Blog Comment email")
  @NotEmpty
  @Email
  private String email;

  @ApiModelProperty(name = "Blog Comment body")
  @NotEmpty
  @Size(min = 12, message = "Title must have 12 characters")
  private String body;
}
