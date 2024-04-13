package com.server.server.interfaces;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.server.models.CurrentModel;
import com.server.server.models.UserModel;

public interface ICurrentRepo extends JpaRepository<CurrentModel, String> {
  Optional<CurrentModel> findCurrentByUser(UserModel user);
}
