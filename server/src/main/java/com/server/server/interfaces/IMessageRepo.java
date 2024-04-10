package com.server.server.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.server.models.MessageModel;

public interface IMessageRepo extends JpaRepository<MessageModel, String>{}