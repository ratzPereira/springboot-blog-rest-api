package com.ratz.blog.DTO;

import lombok.Data;

@Data
public class LoginDTO {

  private String usernameOrEmail;
  private String password;
}
