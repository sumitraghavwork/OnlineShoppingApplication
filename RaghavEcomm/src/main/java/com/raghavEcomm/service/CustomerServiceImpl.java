package com.raghavEcomm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raghavEcomm.exceptions.LoginException;
import com.raghavEcomm.exceptions.OrderException;
import com.raghavEcomm.exceptions.UserException;
import com.raghavEcomm.model.Cart;
import com.raghavEcomm.model.CurrentUserSession;
import com.raghavEcomm.model.Customer;
import com.raghavEcomm.model.Order;
import com.raghavEcomm.model.Product;
import com.raghavEcomm.repository.CartRepo;
import com.raghavEcomm.repository.CurrentUserSessionRepo;
import com.raghavEcomm.repository.CustomerRepo;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepo uRepo;

	@Autowired
	private CurrentUserSessionRepo csdao;

	@Autowired
	private CartRepo cartRepo;

	@Override
	public Customer saveUser(Customer user) throws UserException {

		Customer existingUserName = uRepo.findByUserName(user.getUserName());
		Customer existingUserEmail = uRepo.findByEmail(user.getEmail());

		if (existingUserName != null)
			throw new UserException("Username already exists " + user.getUserName());

		if (existingUserEmail != null)
			throw new UserException("User email exists " + user.getEmail());

		Cart cart = new Cart();
		cart.setCartValue(0);
		cart.setProducts(new HashMap<Product, Integer>());

		user.setCart(cart);
		cart.setCustomer(user);
		Customer savedCustomer = uRepo.save(user);

		return savedCustomer;
	}

	@Override
	public Customer updateUser(Customer user, String customerKey) throws UserException, LoginException {

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

			user.setAddresses(customer.getAddresses());
			user.setCart(customer.getCart());
			user.setOrders(customer.getOrders());
			user.setUserCards(user.getUserCards());

			Customer updatedUser = uRepo.save(user);

			return updatedUser;

		} else {
			throw new UserException("User Not Found");
		}

	}

	@Override
	public Customer deleteUser(String username,String customerKey) throws UserException, LoginException {

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

			uRepo.delete(customer);

			return customer;

		} else {
			throw new UserException("User Not Found");
		}
	}

	@Override
	public Customer findByUserLoginId(Integer userLoginId) throws UserException {

		Optional<Customer> existingUser = uRepo.findById(userLoginId);

		if (existingUser.isPresent())
			return existingUser.get();
		else
			throw new UserException("User does not exists with this userLoginId " + userLoginId);

	}

	@Override
	public Customer findByEmail(String email) throws UserException {

		Customer existingUser = uRepo.findByEmail(email);

		if (existingUser != null)
			return existingUser;
		else
			throw new UserException("User does not exists with this email " + email);

	}

	@Override
	public Customer findByUserName(String userName) throws UserException {
		Customer existingUser = uRepo.findByUserName(userName);

		if (existingUser != null)
			return existingUser;
		else
			throw new UserException("User does not exists with this userName " + userName);
	}

	@Override
	public List<Customer> findAllUsers() throws UserException {

		List<Customer> users = uRepo.findAll();

		if (users.isEmpty())
			throw new UserException("No Users Found");

		return users;
	}

	@Override
	public Customer findByUserNameOrEmail(String userName, String email) throws UserException {

		Customer existingUser = uRepo.findByUserNameOrEmail(userName, email);

		if (existingUser != null)
			return existingUser;
		else
			throw new UserException("User does not exists with this userName or email " + userName + ", " + email);
	}
}
