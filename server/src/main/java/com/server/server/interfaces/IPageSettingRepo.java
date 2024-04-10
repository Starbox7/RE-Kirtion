package com.server.server.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.server.models.PageSettingModel;

public interface IPageSettingRepo extends JpaRepository<PageSettingModel, String> {}
