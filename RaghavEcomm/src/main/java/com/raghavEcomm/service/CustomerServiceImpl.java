package com.raghavEcomm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raghavEcomm.exceptions.LoginException;
import com.raghavEcomm.exceptions.UserException;
import com.raghavEcomm.model.CurrentUserSession;
import com.raghavEcomm.model.Customer;
import com.raghavEcomm.repository.CurrentUserSessionRepo;
import com.raghavEcomm.repository.CustomerRepo;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepo uRepo;

	@Autowired
	private CurrentUserSessionRepo csdao;

	@Override
	public Customer saveUser(Customer user) throws UserException {

		Customer existingUserName = uRepo.findByUserName(user.getUserName());
		Customer existingUserEmail = uRepo.findByEmail(user.getEmail());

		if (existingUserName != null)
			throw new UserException("Username already exists " + user.getUserName());

		if (existingUserEmail != null)
			throw new UserException("User email exists " + user.getEmail());

		return uRepo.save(user);
	}

	@Override
	public Customer updateUser(Customer user, String key) throws UserException, LoginException {

		CurrentUserSession loggedInUser = csdao.findByUuid(key);

		if (loggedInUser == null) {
			throw new UserException("Please provide a valid key to update a customer");
		}

		if (user.getUserLoginId() == loggedInUser.getUserId()) {
			return uRepo.save(user);
		} else
			throw new LoginException("Invalid User Details, please login first");

	}

	@Override
	public Customer deleteUser(String username) throws UserException {

		Customer existingUser = uRepo.findByUserName(username);

		if (existingUser == null)
			throw new UserException("User does not exists with this username " + username);

		uRepo.delete(existingUser);

		return existingUser;
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
