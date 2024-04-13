package com.server.server.interfaces;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.server.models.PersonalspaceModel;
import com.server.server.models.WorkspaceModel;

public interface IPersonalspaceRepo extends JpaRepository<PersonalspaceModel, String> {
  Optional<PersonalspaceModel> findPersonalspaceByWorkspace(WorkspaceModel workspace);
}