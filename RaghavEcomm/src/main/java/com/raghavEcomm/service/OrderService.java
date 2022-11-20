package com.raghavEcomm.service;

import java.util.Optional;

import com.raghavEcomm.model.Order;

public interface OrderService {

	Optional<Order> findById(Integer orderId);

}
