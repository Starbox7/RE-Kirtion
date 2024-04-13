package com.server.server.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.server.models.PageModel;
import com.server.server.models.PersonalspaceModel;

public interface IPageRepo extends JpaRepository<PageModel, String> {
  Optional<PageModel> findPageById(PageModel page);

  List<PageModel> findAllPageByPersonalspace(PersonalspaceModel personalspace); 
}
