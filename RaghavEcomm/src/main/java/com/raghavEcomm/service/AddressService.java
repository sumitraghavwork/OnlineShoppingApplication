package com.raghavEcomm.service;

import java.util.List;

import com.raghavEcomm.exceptions.AddressException;
import com.raghavEcomm.exceptions.LoginException;
import com.raghavEcomm.exceptions.UserException;
import com.raghavEcomm.model.Address;

public interface AddressService {

	public Address addAddress(Address address, String customerKey)
			throws LoginException, UserException, AddressException;

	public String deleteAddress(String customerKey) throws LoginException, UserException, AddressException;

	public Address updateAddress(Address address, String customerKey)
			throws LoginException, UserException, AddressException;

	public Address getAddressByCustomerKey(String customerKey) throws LoginException, UserException, AddressException;

//	public List<Address> getAllAddressOfCustomer(String customerKey)
//			throws LoginException, UserException, AddressException;

}
