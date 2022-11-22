package com.raghavEcomm.service;

import java.util.List;
import java.util.Optional;

import com.raghavEcomm.exceptions.CartException;
import com.raghavEcomm.exceptions.LoginException;
import com.raghavEcomm.exceptions.OrderException;
import com.raghavEcomm.exceptions.UserException;
import com.raghavEcomm.model.Order;

public interface OrderService {

	public Order viewOrder(Integer orderId, String customerKey) throws LoginException, UserException, OrderException;

	public List<Order> viewAllOrder(String customerKey) throws LoginException, UserException, OrderException;

	public Order deleteOrderByOrderId(Integer orderId, String customerKey)
			throws LoginException, UserException, OrderException;

	public Order updateOrderByOrderId(Order order, Integer orderId, String customerKey)
			throws LoginException, UserException, OrderException;

	public Order placeOrder(String customerKey) throws LoginException, UserException, CartException;

}
