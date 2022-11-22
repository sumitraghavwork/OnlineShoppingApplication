package com.raghavEcomm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raghavEcomm.exceptions.AdminException;
import com.raghavEcomm.exceptions.LoginException;
import com.raghavEcomm.exceptions.ProductCategoryException;
import com.raghavEcomm.exceptions.ProductException;
import com.raghavEcomm.model.CurrentUserSession;
import com.raghavEcomm.model.Product;
import com.raghavEcomm.model.ProductCategory;
import com.raghavEcomm.repository.CurrentUserSessionRepo;
import com.raghavEcomm.repository.ProductCategoryRepo;
import com.raghavEcomm.repository.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepo productRepo;

	@Autowired
	private ProductCategoryRepo pcRepo;

	@Autowired
	private CurrentUserSessionRepo csdao;

	@Override
	public Product addProduct(Product product, String sellerKey, Integer categoryId)
			throws LoginException, AdminException, ProductCategoryException {

		CurrentUserSession loggedInUser = csdao.findByUuid(sellerKey);

		if (loggedInUser == null) {
			throw new LoginException("Invalid Key Entered");
		}

		if (loggedInUser.getSeller() == false) {
			throw new AdminException("Unauthorized Access! Only Seller can make changes");
		}

		Optional<ProductCategory> existedCategory = pcRepo.findById(categoryId);

		if (existedCategory.isPresent()) {
			ProductCategory presentCategory = existedCategory.get();
			product.setProductCategory(presentCategory);

			return productRepo.save(product);

		} else {
			throw new ProductCategoryException("Category does not exists with this category Id" + categoryId);
		}

	}

	@Override
	public Product updateProduct(Product product, String sellerKey, Integer productId)
			throws LoginException, AdminException, ProductException {

		CurrentUserSession loggedInUser = csdao.findByUuid(sellerKey);

		if (loggedInUser == null) {
			throw new LoginException("Invalid Key Entered");
		}

		if (loggedInUser.getSeller() == false) {
			throw new AdminException("Unauthorized Access! Only Seller can make changes");
		}

		Optional<Product> existingProduct = productRepo.findById(productId);

		if (existingProduct.isPresent()) {

			Product present = existingProduct.get();

			product.setProductCategory(present.getProductCategory());

			return productRepo.save(product);

		} else {
			throw new ProductException("Product does not exists with this product Id" + productId);
		}
	}

	@Override
	public Product deleteProduct(String sellerKey, Integer productId)
			throws ProductException, LoginException, AdminException {

		CurrentUserSession loggedInUser = csdao.findByUuid(sellerKey);

		if (loggedInUser == null) {
			throw new LoginException("Invalid Key Entered");
		}

		if (loggedInUser.getSeller() == false) {
			throw new AdminException("Unauthorized Access! Only Seller can make changes");
		}

		Optional<Product> existingProduct = productRepo.findById(productId);

		if (existingProduct.isPresent()) {

			Product present = existingProduct.get();

			productRepo.delete(present);

			return present;

		} else {
			throw new ProductException("Product does not exists with this product Id" + productId);
		}
	}

	@Override
	public Product getProductById(Integer productId) throws ProductException {

		Optional<Product> existingProduct = productRepo.findById(productId);

		if (existingProduct.isPresent()) {

			Product present = existingProduct.get();

			return present;

		} else {
			throw new ProductException("Product does not exists with this product Id" + productId);
		}
	}

	@Override
	public List<Product> getAllProducts() throws ProductException {

		List<Product> list = productRepo.findAll();
		if (list.isEmpty()) {
			throw new ProductException("No Products found");
		}

		return list;
	}

	@Override
	public List<Product> getProductsUnderPrice(Integer price) throws ProductException {

		List<Product> list = productRepo.getProductsUnderPrice(price);
		if (list.isEmpty()) {
			throw new ProductException("No Products found");
		}

		return list;
	}

	@Override
	public List<Product> getProductsFromPriceToPrice(Integer fromPrice, Integer toPrice) throws ProductException {

		List<Product> list = productRepo.getProductsFromPriceToPrice(fromPrice, toPrice);
		if (list.isEmpty()) {
			throw new ProductException("No Products found");
		}

		return list;
	}

}
