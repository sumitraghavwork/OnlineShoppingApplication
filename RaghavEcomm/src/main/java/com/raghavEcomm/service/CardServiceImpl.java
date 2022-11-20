package com.raghavEcomm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raghavEcomm.repository.CardRepo;

@Service
public class CardServiceImpl implements CardService{
	
	@Autowired
	private CardRepo cardRepo;
	
}
