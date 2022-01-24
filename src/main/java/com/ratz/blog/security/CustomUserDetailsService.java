package com.ratz.blog.security;


import com.ratz.blog.entity.Role;
import com.ratz.blog.entity.User;
import com.ratz.blog.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user= userRepository.findByEmailOrUsername(username, username).orElseThrow(
        ()-> new UsernameNotFoundException("User not found with user name or email: " + username));

    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
  }

  private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles){

    return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
  }
}
