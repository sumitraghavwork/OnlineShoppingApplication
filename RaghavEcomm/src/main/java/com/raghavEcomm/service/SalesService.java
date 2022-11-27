package com.raghavEcomm.service;

import java.util.List;

import com.raghavEcomm.exceptions.AdminException;
import com.raghavEcomm.exceptions.LoginException;
import com.raghavEcomm.exceptions.OrderException;
import com.raghavEcomm.model.Order;

public interface SalesService {

	public List<Order> getAllSalesHandler(String adminKey) throws LoginException, AdminException, OrderException;

	public List<Order> getSalesOfTodayHandler(String adminKey) throws LoginException, AdminException, OrderException;

	public List<Order> getSalesOfLastWeekHandler(String adminKey) throws LoginException, AdminException, OrderException;

	public List<Order> getSalesOfLastMonthHandler(String adminKey)
			throws LoginException, AdminException, OrderException;

	public List<Order> getSalesOfYearHandler(String adminKey) throws LoginException, AdminException, OrderException;

}
