package com.server.server.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.server.models.UserSettingModel;

public interface IUserSettingRepo extends JpaRepository<UserSettingModel, String> {}
