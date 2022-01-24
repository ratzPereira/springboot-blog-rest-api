package com.ratz.blog.controller;


import com.ratz.blog.DTO.LoginDTO;
import com.ratz.blog.DTO.SignUpDTO;
import com.ratz.blog.entity.Role;
import com.ratz.blog.entity.User;
import com.ratz.blog.repository.RoleRepository;
import com.ratz.blog.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

  private AuthenticationManager authenticationManager;
  private UserRepository userRepository;
  private RoleRepository roleRepository;
  private PasswordEncoder passwordEncoder;


  @PostMapping("/signIn")
  public ResponseEntity<String> authenticateUser(@RequestBody LoginDTO loginDTO){

    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    return new ResponseEntity<>("Authentication successfully", HttpStatus.OK);
  }


  @PostMapping("/signUp")
  public ResponseEntity<String> registerUser(@RequestBody SignUpDTO signUpDTO){

    //add check for username exists
    if (userRepository.existsByUsername(signUpDTO.getUsername())){
      return new ResponseEntity<>("Username with that username already exists", HttpStatus.BAD_REQUEST);
    }

    //add email check
    if (userRepository.existsByEmail(signUpDTO.getEmail())) {
      return new ResponseEntity<>("Username with that email already exists", HttpStatus.BAD_REQUEST);
    }

    //create user
    User user = new User();
    user.setEmail(signUpDTO.getEmail());
    user.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));
    user.setName(signUpDTO.getName());
    user.setUsername(signUpDTO.getUsername());

    //set role to user
    Role role = roleRepository.findByName("ROLE_ADMIN").get();
    user.setRoles(Collections.singleton(role));

    //save user
    userRepository.save(user);

    return new ResponseEntity<>("User created with success", HttpStatus.OK);
  }

}
