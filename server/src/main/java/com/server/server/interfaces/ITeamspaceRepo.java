package com.server.server.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.server.models.TeamspaceModel;
import com.server.server.models.WorkspaceModel;

public interface ITeamspaceRepo extends JpaRepository<TeamspaceModel, String> {
  List<TeamspaceModel> findTAllTeamspaceByWorkspaceUuid(String workspaceUuid);
}
