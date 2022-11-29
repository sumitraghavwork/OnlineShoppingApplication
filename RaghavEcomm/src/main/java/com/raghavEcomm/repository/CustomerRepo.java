package com.raghavEcomm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.raghavEcomm.model.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {

	public Customer findByEmail(String email);

	public Customer findByUserName(String userName);

	public Customer findByUserNameOrEmail(String username, String email);
	
}
