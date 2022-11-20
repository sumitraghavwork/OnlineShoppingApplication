package com.raghavEcomm.service;

import java.util.List;

import com.raghavEcomm.exceptions.AdminException;
import com.raghavEcomm.exceptions.LoginException;
import com.raghavEcomm.exceptions.ProductCategoryException;
import com.raghavEcomm.model.Product;
import com.raghavEcomm.model.ProductCategory;

public interface ProductCategoryService {

	public ProductCategory addCategory(ProductCategory category, String adminKey) throws LoginException, AdminException, ProductCategoryException;

	public ProductCategory updateCategory(ProductCategory category, Integer productCategoryId, String adminKey) throws LoginException, AdminException, ProductCategoryException;

	public ProductCategory getCategoryById(Integer productCategoryId, String adminKey) throws ProductCategoryException, LoginException, AdminException;

	public ProductCategory deleteCategory(Integer productCategoryId, String adminKey) throws ProductCategoryException, LoginException, AdminException;

	public List<ProductCategory> getAllCategory() throws ProductCategoryException;

	public List<Product> getAllProductByCategoryId(Integer productCategoryId) throws ProductCategoryException;

	public List<Product> getAllProductByCategoryname(String productCategoryName) throws ProductCategoryException;

}
