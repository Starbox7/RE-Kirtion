package com.server.server.interfaces;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.server.models.UserModel;

public interface IUserRepo extends JpaRepository<UserModel, String> {
  boolean existsByEmail(String email);

  Optional<UserModel> findUserByToken(String token);

  Optional<UserModel> findUserByEmail(String email);
}
