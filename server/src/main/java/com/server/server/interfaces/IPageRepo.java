package com.server.server.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.server.models.PageModel;
import com.server.server.models.PersonalspaceModel;
import com.server.server.models.TeamspaceModel;

public interface IPageRepo extends JpaRepository<PageModel, String> {
  Optional<PageModel> findPageByUuid(String uuid);

  List<PageModel> findAllPageByPersonalspaceUuid(String personalspaceUuid); 
  List<PageModel> findAllPageByTeamspaceUuid(String teamspaceUuid);
}
