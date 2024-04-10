package com.server.server.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.server.models.PageModel;

public interface IPageRepo extends JpaRepository<PageModel, String> {}
