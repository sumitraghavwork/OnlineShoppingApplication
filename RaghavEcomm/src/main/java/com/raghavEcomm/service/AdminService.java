package com.raghavEcomm.service;

import java.util.List;

import com.raghavEcomm.exceptions.AdminException;
import com.raghavEcomm.exceptions.LoginException;
import com.raghavEcomm.model.Admin;


public interface AdminService {

	public Admin saveUser(Admin user) throws AdminException;

	public Admin updateUser(Admin user, String key) throws AdminException, LoginException;

	public Admin deleteUser(String adminUsername) throws AdminException;

	public Admin findByAdminId(Integer adminId) throws AdminException;

	public Admin findByUserName(String adminUserName) throws AdminException;

	public List<Admin> findAllUsers() throws AdminException;
}
