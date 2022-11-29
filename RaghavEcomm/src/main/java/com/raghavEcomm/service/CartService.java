package com.raghavEcomm.service;

import java.util.List;

import com.raghavEcomm.exceptions.AdminException;
import com.raghavEcomm.exceptions.LoginException;
import com.raghavEcomm.exceptions.ProductException;
import com.raghavEcomm.exceptions.UserException;
import com.raghavEcomm.model.Cart;
import com.raghavEcomm.model.Product;

public interface CartService {

	public Cart addProductToCart(Integer productId, String customerKey)
			throws LoginException, UserException, AdminException, ProductException;

	public Cart increaseProductQuantityInCart(Integer productId, String customerKey)
			throws LoginException, UserException, AdminException, ProductException;

	public Cart reduceProductQuantityInCart(Integer productId, String customerKey)
			throws LoginException, UserException, AdminException, ProductException;

	public Cart deleteProductToCart(Integer productId, String customerKey)
			throws LoginException, UserException, AdminException, ProductException;

	public List<Product> getProductListOfCart(String customerKey)
			throws LoginException, UserException, AdminException, ProductException;

	public Integer getCartValue(String customerKey)
			throws LoginException, UserException, AdminException, ProductException;

	public Cart emptyCart(String customerkey) throws LoginException, UserException, AdminException, ProductException;

	public List<Cart> getAllCartWithProduct(Product product);
}
