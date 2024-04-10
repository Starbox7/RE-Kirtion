package com.server.server.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.server.models.PageSnapshotModel;

public interface IPageSnapshotRepo extends JpaRepository<PageSnapshotModel, String> {}
