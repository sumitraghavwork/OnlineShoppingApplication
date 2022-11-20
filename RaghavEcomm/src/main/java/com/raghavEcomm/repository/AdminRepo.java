package com.raghavEcomm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.raghavEcomm.model.Admin;


@Repository
public interface AdminRepo extends JpaRepository<Admin, Integer>{

	public Admin findByAdminUsername(String userName);

}
