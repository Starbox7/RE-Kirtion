package com.server.server.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.server.models.PaymentModel;

public interface IPaymentRepo extends JpaRepository<PaymentModel, String> {}
