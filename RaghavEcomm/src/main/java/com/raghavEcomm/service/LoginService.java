package com.raghavEcomm.service;

import com.raghavEcomm.exceptions.LoginException;
import com.raghavEcomm.model.AdminDto;
import com.raghavEcomm.model.UserDto;

public interface LoginService {

	public String loginAdmin(AdminDto admin) throws LoginException;

	public String loginUser(UserDto user) throws LoginException;

	public String logoutAdmin(String key) throws LoginException;

	public String logoutUser(String key) throws LoginException;

	public String loginSeller(UserDto user) throws LoginException;

	public String logoutSeller(String key) throws LoginException;

}
