package com.raghavEcomm.service;

import java.util.List;

import com.raghavEcomm.exceptions.LoginException;
import com.raghavEcomm.exceptions.UserException;
import com.raghavEcomm.model.Seller;

public interface SellerService {

	public Seller saveUser(Seller user) throws UserException;

	public Seller updateUser(Seller user, String key) throws UserException, LoginException;

	public Seller deleteUser(String username) throws UserException;

	public Seller findByUserLoginId(Integer userLoginId) throws UserException;

	public Seller findByEmail(String email) throws UserException;

	public Seller findByUserName(String userName) throws UserException;

	public Seller findByUserNameOrEmail(String userName, String email) throws UserException;

	public List<Seller> findAllUsers() throws UserException;
}
