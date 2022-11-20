package com.raghavEcomm.service;

import java.util.List;

import com.raghavEcomm.exceptions.AdminException;
import com.raghavEcomm.exceptions.LoginException;
import com.raghavEcomm.exceptions.ProductCategoryException;
import com.raghavEcomm.exceptions.ProductException;
import com.raghavEcomm.model.Product;

public interface ProductService {

	public Product addProduct(Product product, String sellerKey, Integer categoryId)
			throws LoginException, AdminException, ProductCategoryException;

	public Product updateProduct(Product product, String sellerKey, Integer productId)
			throws LoginException, AdminException, ProductException;

	public Product deleteProduct(String sellerKey, Integer productId)
			throws LoginException, AdminException, ProductException;

	public Product getProductById(Integer productId) throws ProductException;

	public List<Product> getAllProducts() throws ProductException;

	public List<Product> getProductsUnderPrice(Integer price) throws ProductException;

	public List<Product> getProductsFromPriceToPrice(Integer fromPrice, Integer toPrice) throws ProductException;

}
