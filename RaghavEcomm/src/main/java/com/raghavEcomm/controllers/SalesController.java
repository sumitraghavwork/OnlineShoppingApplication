package com.raghavEcomm.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.raghavEcomm.exceptions.AdminException;
import com.raghavEcomm.exceptions.LoginException;
import com.raghavEcomm.exceptions.OrderException;
import com.raghavEcomm.model.Order;
import com.raghavEcomm.service.SalesService;

@RestController
@RequestMapping("/sales")
public class SalesController {

	@Autowired
	private SalesService salesService;

	@GetMapping("/salesAllOrders")
	public ResponseEntity<List<Order>> getAllSalesHandler(@RequestParam String adminKey)
			throws LoginException, AdminException, OrderException {

		List<Order> orders = salesService.getAllSalesHandler(adminKey);

		return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);

	}

	@GetMapping("/salesOfToday")
	public ResponseEntity<List<Order>> getSalesOfTodayHandler(@RequestParam String adminKey)
			throws LoginException, AdminException, OrderException {

		List<Order> orders = salesService.getSalesOfTodayHandler(adminKey);

		return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);

	}

	@GetMapping("/salesOfLastWeek")
	public ResponseEntity<List<Order>> getSalesOfLastWeekHandler(@RequestParam String adminKey)
			throws LoginException, AdminException, OrderException {

		List<Order> orders = salesService.getSalesOfLastWeekHandler(adminKey);

		return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);

	}

	@GetMapping("/salesOfLastMonth")
	public ResponseEntity<List<Order>> getSalesOfLastMonthHandler(@RequestParam String adminKey)
			throws LoginException, AdminException, OrderException {
		
		List<Order> orders = salesService.getSalesOfLastMonthHandler(adminKey);

		return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);

	}

	@GetMapping("/salesOfThisYear")
	public ResponseEntity<List<Order>> getSalesOfYearHandler(@RequestParam String adminKey) throws LoginException, AdminException, OrderException {

		List<Order> orders = salesService.getSalesOfYearHandler(adminKey);

		return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);

	}

}
