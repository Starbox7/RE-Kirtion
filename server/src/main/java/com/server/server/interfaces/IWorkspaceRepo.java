package com.server.server.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.server.models.WorkspaceModel;

public interface IWorkspaceRepo extends JpaRepository<WorkspaceModel, String> {}
