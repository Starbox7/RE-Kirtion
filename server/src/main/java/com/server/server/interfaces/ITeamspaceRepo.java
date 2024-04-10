package com.server.server.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.server.models.TeamspaceModel;

public interface ITeamspaceRepo extends JpaRepository<TeamspaceModel, String> {}
