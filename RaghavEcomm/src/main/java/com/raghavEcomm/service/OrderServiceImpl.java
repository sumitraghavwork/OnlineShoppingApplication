package com.raghavEcomm.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raghavEcomm.model.Order;
import com.raghavEcomm.repository.OrderRepo;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepo orderRepo;

	@Override
	public Optional<Order> findById(Integer orderId) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}
