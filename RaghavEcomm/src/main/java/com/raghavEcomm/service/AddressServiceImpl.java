package com.raghavEcomm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raghavEcomm.repository.AddressRepo;

@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private AddressRepo addressRepo;
	
	
	
}
