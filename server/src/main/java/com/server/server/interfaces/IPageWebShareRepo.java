package com.server.server.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.server.models.PageWebShareModel;

public interface IPageWebShareRepo extends JpaRepository<PageWebShareModel, String> {}
