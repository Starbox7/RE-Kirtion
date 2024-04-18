package com.server.server.interfaces;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.server.models.WorkspaceModel;

public interface IWorkspaceRepo extends JpaRepository<WorkspaceModel, String> {
  Optional<WorkspaceModel> findWorkspaceByUuid(String uuid);
  Optional<WorkspaceModel> findWorkspaceByUserUuid(String userUuid);
}
