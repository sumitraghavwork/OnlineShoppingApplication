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

import com.raghavEcomm.exceptions.AdminException;
import com.raghavEcomm.exceptions.CartException;
import com.raghavEcomm.exceptions.LoginException;
import com.raghavEcomm.model.Admin;
import com.raghavEcomm.model.Cart;
import com.raghavEcomm.service.CartService;

@RestController
@RequestMapping("/cartController")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
//	@GetMapping("/carts/{cartId}")
//	public ResponseEntity<Cart> getCartDetailsHandler(@PathVariable Integer cartId) throws CartException {
//
//		
//
//		return new ResponseEntity<Cart>(existingCart, HttpStatus.OK);
//
//	}
//
//	@GetMapping("/carts")
//	public ResponseEntity<List<Cart>> getAllCartDetailsHandler() throws CartException {
//
//
//		return new ResponseEntity<List<Cart>>(cartList, HttpStatus.OK);
//
//	}
//
//	@PostMapping("/carts")
//	public ResponseEntity<Admin> registerCartHandler(@Valid @RequestBody Cart cart) throws CartException {
//
//
//		return new ResponseEntity<Admin>(savedCart, HttpStatus.OK);
//
//	}
//
//	@PutMapping("/carts")
//	public ResponseEntity<Cart> updateCartHandler(@Valid @RequestBody Cart cart, @RequestParam("key") String key)
//			throws CartException, LoginException {
//
//
//		return new ResponseEntity<Cart>(updatedCart, HttpStatus.OK);
//
//	}
//
//	@DeleteMapping("/admins/{cartId}")
//	public ResponseEntity<Admin> deleteCartHandler(@PathVariable("cartId") Integer cartId)
//			throws CartException, LoginException {
//
//
//		return new ResponseEntity<Admin>(deletedCart, HttpStatus.OK);
//
//	}
}
