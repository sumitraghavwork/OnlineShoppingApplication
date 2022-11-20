package com.raghavEcomm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.raghavEcomm.model.Seller;

@Repository
public interface SellerRepo extends JpaRepository<Seller, Integer> {

	public Seller findByEmail(String email);

	public Seller findByUserName(String userName);

	public Seller findByUserNameOrEmail(String username, String email);

}
