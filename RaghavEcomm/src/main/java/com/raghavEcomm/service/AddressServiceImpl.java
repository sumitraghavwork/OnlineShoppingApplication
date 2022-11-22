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

			Address savedAdress = addressRepo.save(address);

			customer.getAddresses().add(savedAdress);

			return savedAdress;

		} else {
			throw new UserException("User Not Found");
		}
	}

	@Override
	public String deleteAddress(Integer addressId, String customerKey)
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

			List<Address> list = customer.getAddresses();

			boolean flag = list.removeIf((c) -> c.getAddressId() == addressId);

			if (flag) {

				customer.setAddresses(list);

				uRepo.save(customer);

				addressRepo.deleteById(addressId);

				return "Card Deleted Succesfully!";
			} else {
				throw new AddressException("No Address found for this user with addressid: " + addressId);
			}
		} else {
			throw new UserException("User Not Found");
		}
	}

	@Override
	public Address updateAddress(Integer addressId, Address address, String customerKey)
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

			List<Address> list = customer.getAddresses();

			boolean flag = list.removeIf((c) -> c.getAddressId() == addressId);

			if (flag) {

				Address savedAdress = addressRepo.save(address);

				customer.getAddresses().add(savedAdress);

				uRepo.save(customer);

				return savedAdress;
			} else {
				throw new AddressException("No Address found for this user with addressid: " + addressId);
			}

		} else {
			throw new UserException("User Not Found");
		}
	}

	@Override
	public Address getAddressById(Integer addressid, String customerKey)
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

			List<Address> list = customer.getAddresses();

			Address address = null;
			for (Address ad : list) {
				if (ad.getAddressId() == addressid) {
					address = ad;
					break;
				}
			}
			if (address != null)
				return address;
			else
				throw new AddressException("No Address found for this user with addressid: " + addressid);

		} else {
			throw new UserException("User Not Found");
		}
	}

	@Override
	public List<Address> getAllAddressOfCustomer(String customerKey)
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

			List<Address> list = customer.getAddresses();

			if (list.isEmpty() == false)
				return list;
			else
				throw new AddressException("No Address found for this user");

		} else {
			throw new UserException("User Not Found");
		}
	}

}
