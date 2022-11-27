package com.raghavEcomm.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.raghavEcomm.exceptions.AdminException;
import com.raghavEcomm.exceptions.LoginException;
import com.raghavEcomm.exceptions.OrderException;
import com.raghavEcomm.model.CurrentUserSession;
import com.raghavEcomm.model.Order;
import com.raghavEcomm.repository.CurrentUserSessionRepo;
import com.raghavEcomm.repository.OrderRepo;

public class SalesServiceImpl implements SalesService {

	@Autowired
	private CurrentUserSessionRepo csdao;

	@Autowired
	private OrderRepo orderRepo;

	@Autowired
	private OrderService orderService;

	@Override
	public List<Order> getAllSalesHandler(String adminKey) throws LoginException, AdminException, OrderException {

		CurrentUserSession loggedInUser = csdao.findByUuid(adminKey);

		if (loggedInUser == null) {
			throw new LoginException("Invalid Key Entered");
		}

		if (loggedInUser.getAdmin() == false) {
			throw new AdminException("Unauthorized Access! Only Admin can make changes");
		}

		List<Order> orders = orderRepo.findAll();

		if (orders == null)
			throw new OrderException("No Orders found.");

		return orders;
	}

	@Override
	public List<Order> getSalesOfTodayHandler(String adminKey) throws LoginException, AdminException, OrderException {

		CurrentUserSession loggedInUser = csdao.findByUuid(adminKey);

		if (loggedInUser == null) {
			throw new LoginException("Invalid Key Entered");
		}

		if (loggedInUser.getAdmin() == false) {
			throw new AdminException("Unauthorized Access! Only Admin can make changes");
		}

		List<Order> orders = orderRepo.findByOrderDate(LocalDate.now());

		if (orders == null)
			throw new OrderException("No Orders found.");

		return orders;
	}

	@Override
	public List<Order> getSalesOfLastWeekHandler(String adminKey)
			throws LoginException, AdminException, OrderException {

		CurrentUserSession loggedInUser = csdao.findByUuid(adminKey);

		if (loggedInUser == null) {
			throw new LoginException("Invalid Key Entered");
		}

		if (loggedInUser.getAdmin() == false) {
			throw new AdminException("Unauthorized Access! Only Admin can make changes");
		}

		List<Order> orders = orderRepo.findByOrderDateGreaterThanEqual(LocalDate.now().minusDays(7));

		if (orders == null)
			throw new OrderException("No Orders found.");

		return orders;
	}

	@Override
	public List<Order> getSalesOfLastMonthHandler(String adminKey)
			throws LoginException, AdminException, OrderException {

		CurrentUserSession loggedInUser = csdao.findByUuid(adminKey);

		if (loggedInUser == null) {
			throw new LoginException("Invalid Key Entered");
		}

		if (loggedInUser.getAdmin() == false) {
			throw new AdminException("Unauthorized Access! Only Admin can make changes");
		}

		List<Order> orders = orderRepo.findByOrderDateGreaterThanEqual(LocalDate.now().minusMonths(1));

		if (orders == null)
			throw new OrderException("No Orders found.");

		return orders;

	}

	@Override
	public List<Order> getSalesOfYearHandler(String adminKey) throws LoginException, AdminException, OrderException {

		CurrentUserSession loggedInUser = csdao.findByUuid(adminKey);

		if (loggedInUser == null) {
			throw new LoginException("Invalid Key Entered");
		}

		if (loggedInUser.getAdmin() == false) {
			throw new AdminException("Unauthorized Access! Only Admin can make changes");
		}

		List<Order> orders = orderRepo.findByOrderDateGreaterThanEqual(LocalDate.now().minusYears(1));

		if (orders == null)
			throw new OrderException("No Orders found.");

		return orders;

	}

}
