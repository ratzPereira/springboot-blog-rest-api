package com.ratz.blog.repository;

import com.ratz.blog.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

  Optional<Role> findByName(String name);
}
