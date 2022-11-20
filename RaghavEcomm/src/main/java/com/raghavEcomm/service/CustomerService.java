package com.raghavEcomm.service;

import java.util.List;

import com.raghavEcomm.exceptions.LoginException;
import com.raghavEcomm.exceptions.UserException;
import com.raghavEcomm.model.Customer;

public interface CustomerService {

	public Customer saveUser(Customer user) throws UserException;

	public Customer updateUser(Customer user, String key) throws UserException, LoginException;

	public Customer deleteUser(String username) throws UserException;

	public Customer findByUserLoginId(Integer userLoginId) throws UserException;

	public Customer findByEmail(String email) throws UserException;

	public Customer findByUserName(String userName) throws UserException;

	public Customer findByUserNameOrEmail(String userName, String email) throws UserException;

	public List<Customer> findAllUsers() throws UserException;
}
