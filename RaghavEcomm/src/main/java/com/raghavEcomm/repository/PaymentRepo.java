package com.raghavEcomm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.raghavEcomm.model.Payment;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Integer> {

}
