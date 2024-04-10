package com.server.server.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.server.models.VatModel;

public interface IVatRepo extends JpaRepository<VatModel, String> {}
