package com.raghavEcomm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raghavEcomm.exceptions.AddressException;
import com.raghavEcomm.exceptions.LoginException;
import com.raghavEcomm.exceptions.UserException;
import com.raghavEcomm.model.Address;
import com.raghavEcomm.model.CurrentUserSession;
import com.raghavEcomm.model.Customer;
import com.raghavEcomm.repository.AddressRepo;
import com.raghavEcomm.repository.CurrentUserSessionRepo;
import com.raghavEcomm.repository.CustomerRepo;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepo addressRepo;

	@Autowired
	private CustomerRepo uRepo;

	@Autowired
	private CurrentUserSessionRepo csdao;

	@Override
	public Address addAddress(Address address, String customerKey)
			throws LoginException, UserException, AddressException {

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

			customer.setAddresses(address);

			Address savedAdress = addressRepo.save(address);

			return savedAdress;

		} else {
			throw new UserException("User Not Found");
		}
	}

	@Override
	public String deleteAddress(String customerKey) throws LoginException, UserException, AddressException {

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

			Address address = customer.getAddresses();

			if (address != null) {

				addressRepo.delete(address);

				return "Card Deleted Succesfully!";
			} else {
				throw new AddressException("No Address found for this user");
			}
		} else {
			throw new UserException("User Not Found");
		}
	}

	@Override
	public Address updateAddress(Address address, String customerKey)
			throws LoginException, UserException, AddressException {

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

			Address existingaddress = customer.getAddresses();

			if (existingaddress != null) {

				customer.setAddresses(address);

				Address savedaddress = addressRepo.save(address);

				return savedaddress;
			} else {
				throw new AddressException("No Address found for this user");
			}

		} else {
			throw new UserException("User Not Found");
		}
	}

	@Override
	public Address getAddressByCustomerKey(String customerKey) throws LoginException, UserException, AddressException {

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

			Address existingaddress = customer.getAddresses();

			if (existingaddress != null) {

				return existingaddress;
			} else {
				throw new AddressException("No Address found for this user");
			}

		} else {
			throw new UserException("User Not Found");
		}
	}

//	@Override
//	public List<Address> getAllAddressOfCustomer(String customerKey)
//			throws LoginException, UserException, AddressException {
//
//		CurrentUserSession loggedInUser = csdao.findByUuid(customerKey);
//
//		if (loggedInUser == null) {
//			throw new LoginException("Invalid Key Entered");
//		}
//
//		if (loggedInUser.getCustomer() == false) {
//			throw new UserException("Unauthorized Access! Only Customer can make changes");
//		}
//
//		Optional<Customer> existingUser = uRepo.findById(loggedInUser.getUserId());
//
//		if (existingUser.isPresent()) {
//			Customer customer = existingUser.get();
//
//			List<Address> list = customer.getAddresses();
//
//			if (list.isEmpty() == false)
//				return list;
//			else
//				throw new AddressException("No Address found for this user");
//
//		} else {
//			throw new UserException("User Not Found");
//		}
//	}

}
