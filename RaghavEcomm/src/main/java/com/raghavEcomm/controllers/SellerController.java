package com.raghavEcomm.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.raghavEcomm.exceptions.LoginException;
import com.raghavEcomm.exceptions.UserException;
import com.raghavEcomm.model.Seller;
import com.raghavEcomm.service.SellerService;

@RestController
@RequestMapping("/sellerController")
public class SellerController {

	@Autowired
	private SellerService userService;

	@GetMapping("/sellers/{username}")
	public ResponseEntity<Seller> getSellerDetailsHandler(@PathVariable String username) throws UserException {

		Seller existingUser = userService.findByUserName(username);

		return new ResponseEntity<Seller>(existingUser, HttpStatus.OK);

	}

	@GetMapping("/sellers")
	public ResponseEntity<List<Seller>> getAllSellersDetailsHandler() throws UserException {

		List<Seller> userList = userService.findAllUsers();

		return new ResponseEntity<List<Seller>>(userList, HttpStatus.OK);

	}

	@PostMapping("/sellers")
	public ResponseEntity<Seller> registerSellerHandler(@Valid @RequestBody Seller user) throws UserException {

		Seller savedUser = userService.saveUser(user);

		return new ResponseEntity<Seller>(savedUser, HttpStatus.OK);

	}

	@PutMapping("/sellers")
	public ResponseEntity<Seller> updateSellerHandler(@Valid @RequestBody Seller user, @RequestParam("key") String key)
			throws UserException, LoginException {

		Seller updatedUser = userService.updateUser(user, key);

		return new ResponseEntity<Seller>(updatedUser, HttpStatus.OK);

	}

	@DeleteMapping("/sellers/{username}")
	public ResponseEntity<Seller> deleteSellerHandler(@PathVariable("username") String username)
			throws UserException, LoginException {

		Seller updatedUser = userService.deleteUser(username);

		return new ResponseEntity<Seller>(updatedUser, HttpStatus.OK);

	}
}
