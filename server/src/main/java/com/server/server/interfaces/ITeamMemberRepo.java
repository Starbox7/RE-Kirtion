package com.server.server.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.server.models.TeamMemberModel;

public interface ITeamMemberRepo extends JpaRepository<TeamMemberModel, String> {}
