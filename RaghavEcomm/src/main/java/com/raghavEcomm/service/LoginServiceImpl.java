package com.raghavEcomm.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raghavEcomm.exceptions.LoginException;
import com.raghavEcomm.model.Admin;
import com.raghavEcomm.model.AdminDto;
import com.raghavEcomm.model.CurrentUserSession;
import com.raghavEcomm.model.Customer;
import com.raghavEcomm.model.Seller;
import com.raghavEcomm.model.UserDto;
import com.raghavEcomm.repository.AdminRepo;
import com.raghavEcomm.repository.CurrentUserSessionRepo;
import com.raghavEcomm.repository.CustomerRepo;
import com.raghavEcomm.repository.SellerRepo;

import net.bytebuddy.utility.RandomString;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private AdminRepo adminRepo;

	@Autowired
	private SellerRepo sellerRepo;

	@Autowired
	private CustomerRepo userRepo;

	@Autowired
	private CurrentUserSessionRepo currUserSession;

	@Override
	public String loginAdmin(AdminDto admin) throws LoginException {

		Admin existingUser = adminRepo.findByAdminUsername(admin.getAdminUsername());

		if (existingUser == null)
			throw new LoginException("Invalid credentials. Username does not exist " + admin.getAdminUsername());

		Optional<CurrentUserSession> validCustomerSessionOpt = currUserSession.findById(existingUser.getAdminId());

		if (validCustomerSessionOpt.isPresent()) {

			throw new LoginException("User already Logged In with this username");

		}

		if (existingUser.getAdminPassword().equals(admin.getAdminPassword())) {

			String key = RandomString.make(6);

			Boolean isAdmin = true;
			Boolean isSeller = false;
			Boolean isCustomer = false;

			CurrentUserSession currentUserSession = new CurrentUserSession(existingUser.getAdminId(), key, isAdmin,
					isSeller, isCustomer, LocalDateTime.now());

			currUserSession.save(currentUserSession);

			return currentUserSession.toString();
		} else
			throw new LoginException("Please Enter a valid password");

	}

	@Override
	public String loginUser(UserDto user) throws LoginException {

		Customer existingUser = userRepo.findByUserName(user.getUserName());

		if (existingUser == null)
			throw new LoginException("Invalid credentials. Username does not exist " + user.getUserName());

		Optional<CurrentUserSession> validCustomerSessionOpt = currUserSession.findById(existingUser.getUserLoginId());

		if (validCustomerSessionOpt.isPresent()) {

			throw new LoginException("User already Logged In with this username");

		}

		if (existingUser.getPassword().equals(user.getPassword())) {

			String key = RandomString.make(6);

			Boolean isAdmin = false;
			Boolean isSeller = false;
			Boolean isCustomer = true;

			CurrentUserSession currentUserSession = new CurrentUserSession(existingUser.getUserLoginId(), key, isAdmin,
					isSeller, isCustomer, LocalDateTime.now());

			currUserSession.save(currentUserSession);

			return currentUserSession.toString();
		} else
			throw new LoginException("Please Enter a valid password");
	}

	@Override
	public String logoutAdmin(String key) throws LoginException {

		CurrentUserSession validCustomerSession = currUserSession.findByUuid(key);

		if (validCustomerSession == null) {
			throw new LoginException("User Not Logged In with this username");

		}

		currUserSession.delete(validCustomerSession);

		return "Logged Out !";
	}

	@Override
	public String logoutUser(String key) throws LoginException {

		CurrentUserSession validCustomerSession = currUserSession.findByUuid(key);

		if (validCustomerSession == null) {
			throw new LoginException("User Not Logged In with this username");

		}

		currUserSession.delete(validCustomerSession);

		return "Logged Out !";
	}

	@Override
	public String loginSeller(UserDto user) throws LoginException {

		Seller existingUser = sellerRepo.findByUserName(user.getUserName());

		if (existingUser == null)
			throw new LoginException("Invalid credentials. Username does not exist " + user.getUserName());

		Optional<CurrentUserSession> validCustomerSessionOpt = currUserSession
				.findById(existingUser.getSellerLoginId());

		if (validCustomerSessionOpt.isPresent()) {

			throw new LoginException("User already Logged In with this username");

		}

		if (existingUser.getPassword().equals(user.getPassword())) {

			String key = RandomString.make(6);

			Boolean isAdmin = false;
			Boolean isSeller = true;
			Boolean isCustomer = false;

			CurrentUserSession currentUserSession = new CurrentUserSession(existingUser.getSellerLoginId(), key,
					isAdmin, isSeller, isCustomer, LocalDateTime.now());

			currUserSession.save(currentUserSession);

			return currentUserSession.toString();
		} else
			throw new LoginException("Please Enter a valid password");
	}

	@Override
	public String logoutSeller(String key) throws LoginException {
		
		CurrentUserSession validCustomerSession = currUserSession.findByUuid(key);

		if (validCustomerSession == null) {
			throw new LoginException("User Not Logged In with this username");

		}

		currUserSession.delete(validCustomerSession);

		return "Logged Out !";
	}

}
