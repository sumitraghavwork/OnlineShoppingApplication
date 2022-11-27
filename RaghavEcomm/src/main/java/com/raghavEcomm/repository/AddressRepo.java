package com.raghavEcomm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.raghavEcomm.model.Address;

@Repository
public interface AddressRepo extends JpaRepository<Address, Integer> {

	public List<Address> findByAddressType(String addressType);

	public List<Address> findByDistrict(String district);

	public List<Address> findByLandmark(String landmark);

	public List<Address> findByPincode(String pincode);

	public List<Address> findByState(String state);

}
