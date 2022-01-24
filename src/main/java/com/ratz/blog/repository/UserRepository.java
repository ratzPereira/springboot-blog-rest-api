package com.ratz.blog.repository;

import com.ratz.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);
  Optional<User> findByEmailOrUsername(String email, String userName);
  Optional<User> findByUsername(String userName);
  Boolean existsByEmail(String email);
  Boolean existsByUsername(String userName);
}
