package com.raghavEcomm.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raghavEcomm.exceptions.AddressException;
import com.raghavEcomm.exceptions.CartException;
import com.raghavEcomm.exceptions.LoginException;
import com.raghavEcomm.exceptions.OrderException;
import com.raghavEcomm.exceptions.UserException;
import com.raghavEcomm.model.Address;
import com.raghavEcomm.model.Cart;
import com.raghavEcomm.model.CurrentUserSession;
import com.raghavEcomm.model.Customer;
import com.raghavEcomm.model.Order;
import com.raghavEcomm.model.Product;
import com.raghavEcomm.repository.CurrentUserSessionRepo;
import com.raghavEcomm.repository.CustomerRepo;
import com.raghavEcomm.repository.OrderRepo;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepo orderRepo;

	@Autowired
	private CustomerRepo uRepo;

	@Autowired
	private CurrentUserSessionRepo csdao;

	@Override
	public Order viewOrder(Integer orderId, String customerKey) throws LoginException, UserException, OrderException {

		CurrentUserSession loggedInUser = csdao.findByUuid(customerKey);

		if (loggedInUser == null) {
			throw new LoginException("Invalid Key Entered");
		}

		if (loggedInUser.getCustomer() == false) {
			throw new UserException("Unauthorized Access! Only Customer can make changes");
		}

		Optional<Customer> existingUser = uRepo.findById(loggedInUser.getUserId());

		if (existingUser.isPresent()) {

			Customer customer = existingUser.get();

			Optional<Order> existingOrder = orderRepo.findById(orderId);

			if (existingOrder.isPresent()) {

				Order savedOrder = existingOrder.get();

				if (savedOrder.getCustomer().getUserLoginId() == customer.getUserLoginId()) {
					return savedOrder;
				} else {
					throw new UserException("Invalid User Details for Order Id: " + orderId);
				}

			} else {
				throw new OrderException("No order found with this orderId: " + orderId);
			}

		} else {
			throw new UserException("User Not Found");
		}
	}

	@Override
	public List<Order> viewAllOrder(String customerKey) throws LoginException, UserException, OrderException {

		CurrentUserSession loggedInUser = csdao.findByUuid(customerKey);

		if (loggedInUser == null) {
			throw new LoginException("Invalid Key Entered");
		}

		if (loggedInUser.getCustomer() == false) {
			throw new UserException("Unauthorized Access! Only Customer can make changes");
		}

		Optional<Customer> existingUser = uRepo.findById(loggedInUser.getUserId());

		if (existingUser.isPresent()) {

			Customer customer = existingUser.get();

			List<Order> list = customer.getOrders();

			if (list.size() > 0) {

				return list;

			} else {
				throw new OrderException("No order found");
			}

		} else {
			throw new UserException("User Not Found");
		}
	}

	@Override
	public Order deleteOrderByOrderId(Integer orderId, String customerKey)
			throws OrderException, UserException, LoginException {

		CurrentUserSession loggedInUser = csdao.findByUuid(customerKey);

		if (loggedInUser == null) {
			throw new LoginException("Invalid Key Entered");
		}

		if (loggedInUser.getCustomer() == false) {
			throw new UserException("Unauthorized Access! Only Customer can make changes");
		}

		Optional<Customer> existingUser = uRepo.findById(loggedInUser.getUserId());

		if (existingUser.isPresent()) {

			Customer customer = existingUser.get();

			Optional<Order> existingOrder = orderRepo.findById(orderId);

			if (existingOrder.isPresent()) {

				Order savedOrder = existingOrder.get();

				if (savedOrder.getCustomer().getUserLoginId() == customer.getUserLoginId()) {

					orderRepo.delete(savedOrder);

					return savedOrder;

				} else {
					throw new UserException("Invalid User Details for Order Id: " + orderId);
				}

			} else {
				throw new OrderException("No order found with this orderId: " + orderId);
			}

		} else {
			throw new UserException("User Not Found");
		}
	}

	@Override
	public Order updateOrderByOrderId(Order order, Integer orderId, String customerKey)
			throws LoginException, UserException, OrderException {

		CurrentUserSession loggedInUser = csdao.findByUuid(customerKey);

		if (loggedInUser == null) {
			throw new LoginException("Invalid Key Entered");
		}

		if (loggedInUser.getCustomer() == false) {
			throw new UserException("Unauthorized Access! Only Customer can make changes");
		}

		Optional<Customer> existingUser = uRepo.findById(loggedInUser.getUserId());

		if (existingUser.isPresent()) {

			Customer customer = existingUser.get();

			Optional<Order> existingOrder = orderRepo.findById(orderId);

			if (existingOrder.isPresent()) {

				Order savedOrder = existingOrder.get();

				if (savedOrder.getCustomer().getUserLoginId() == customer.getUserLoginId()) {

					order.setCustomer(customer);

					return orderRepo.save(order);

				} else {
					throw new UserException("Invalid User Details for Order Id: " + orderId);
				}
			} else {
				throw new OrderException("No order found with this orderId: " + orderId);
			}

		} else {
			throw new UserException("User Not Found");
		}
	}

	@Override
	public Order placeOrder(String customerKey) throws LoginException, UserException, CartException, AddressException {
		
		CurrentUserSession loggedInUser = csdao.findByUuid(customerKey);

		if (loggedInUser == null) {
			throw new LoginException("Invalid Key Entered");
		}

		if (loggedInUser.getCustomer() == false) {
			throw new UserException("Unauthorized Access! Only Customer can make changes");
		}

		Optional<Customer> existingUser = uRepo.findById(loggedInUser.getUserId());

		if (existingUser.isPresent()) {
			
			Customer customer = existingUser.get();

			Cart cart = customer.getCart();

			Map<Product, Integer> productMap = cart.getProducts();

			if (productMap.isEmpty()) {
				throw new CartException("Empty cart Found");
			}

			Order newOrder = new Order();

			newOrder.setCustomer(customer);
			newOrder.setOrderDate(LocalDate.now());
			newOrder.setProducts(productMap);
			newOrder.setOrderStatus("Placed");
			Address address = customer.getAddresses();
			
			if(address==null) {
				throw new AddressException("No Address found for this User.");
			}
			
			newOrder.setShippingAddress(address);
			newOrder.setOrderAmount(cart.getCartValue());

			Order savedOrder = orderRepo.save(newOrder);
			customer.getOrders().add(savedOrder);
			uRepo.save(customer);

			return savedOrder;

		} else {
			throw new UserException("User Not Found");
		}
	}

}
