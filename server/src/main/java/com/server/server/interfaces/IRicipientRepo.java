package com.server.server.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.server.models.RicipientModel;

public interface IRicipientRepo extends JpaRepository<RicipientModel, String> {}
