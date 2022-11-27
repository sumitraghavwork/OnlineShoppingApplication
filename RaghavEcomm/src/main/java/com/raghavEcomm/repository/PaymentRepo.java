package com.raghavEcomm.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.raghavEcomm.model.Order;
import com.raghavEcomm.model.Payment;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Integer> {

	public List<Payment> findByOrder(Order order);

	public List<Payment> findByPaid(Boolean paid);

	public List<Payment> findByPaymentDate(LocalDate paymentDate);

	public List<Payment> findByPaymentDateGreaterThanEqual(LocalDate paymentDate);

	public List<Payment> findByAmount(Integer amount);

	public List<Payment> findTop5ByAmount(Integer amount);

	public List<Payment> findByAmountBetween(Integer s_amount, Integer e_amount);

	public List<Payment> findByAmountGreaterThanEqual(Integer amount);

}
