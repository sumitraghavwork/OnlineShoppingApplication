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
import com.raghavEcomm.exceptions.LoginException;
import com.raghavEcomm.exceptions.ProductCategoryException;
import com.raghavEcomm.model.Product;
import com.raghavEcomm.model.ProductCategory;
import com.raghavEcomm.service.ProductCategoryService;

//import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/productCategoryController")
public class ProductCategoryController {

	@Autowired
	private ProductCategoryService pcService;

	/**********************************
	 * GET MAPPINGS START
	 ************************************/

	@GetMapping("/category/{categoryId}")
	public ResponseEntity<ProductCategory> getCategoryByIdHandler(@PathVariable("categoryID") Integer categoryId,
			@RequestParam String adminKey) throws ProductCategoryException, LoginException, AdminException {

		ProductCategory savedCategory = pcService.getCategoryById(categoryId, adminKey);

		return new ResponseEntity<ProductCategory>(savedCategory, HttpStatus.OK);

	}

	@GetMapping("/category")
	public ResponseEntity<List<ProductCategory>> getAllCategoriesHandler() throws ProductCategoryException {

		List<ProductCategory> savedCategory = pcService.getAllCategory();

		return new ResponseEntity<List<ProductCategory>>(savedCategory, HttpStatus.OK);

	}

	@GetMapping("/allProductsOfCategoryById")
	public ResponseEntity<List<Product>> getAllProductsOfCategoryByIdHandler(@RequestParam Integer categoryId)
			throws ProductCategoryException {

		List<Product> products = pcService.getAllProductByCategoryId(categoryId);

		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);

	}

	@GetMapping("/allProductsOfCategoryByCategoryName")
	public ResponseEntity<List<Product>> getAllProductsOfCategoryByNameHandler(@RequestParam String categoryName)
			throws ProductCategoryException {

		List<Product> products = pcService.getAllProductByCategoryname(categoryName);

		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);

	}

	/**********************************
	 * GET MAPPINGS END
	 ************************************/

	/**********************************
	 * POST MAPPINGS START
	 ************************************/

	@PostMapping("/category")
	public ResponseEntity<ProductCategory> addProductCategoryHandler(@Valid @RequestBody ProductCategory category,
			@RequestParam String adminKey) throws LoginException, AdminException, ProductCategoryException {

		ProductCategory savedCategory = pcService.addCategory(category, adminKey);

		return new ResponseEntity<ProductCategory>(savedCategory, HttpStatus.OK);

	}

	/**********************************
	 * POST MAPPINGS END
	 ************************************/

	/**********************************
	 * PUT MAPPINGS START
	 ************************************/

	@PutMapping("/category/{categoryId}")
	public ResponseEntity<ProductCategory> updateProductCategoryHandler(@PathVariable("categoryID") Integer categoryId,
			@RequestParam String adminKey, @Valid @RequestBody ProductCategory category)
			throws LoginException, AdminException, ProductCategoryException {

		ProductCategory savedCategory = pcService.updateCategory(category, categoryId, adminKey);

		return new ResponseEntity<ProductCategory>(savedCategory, HttpStatus.OK);

	}

	/**********************************
	 * PUT MAPPINGS END
	 ************************************/

	/**********************************
	 * DELLETE MAPPINGS START
	 ************************************/

	@DeleteMapping("/category/{categoryId}")
	public ResponseEntity<ProductCategory> deleteProductCategoryHandler(@PathVariable("categoryID") Integer categoryId,
			@RequestParam String adminKey) throws ProductCategoryException, LoginException, AdminException {

		ProductCategory savedCategory = pcService.deleteCategory(categoryId, adminKey);

		return new ResponseEntity<ProductCategory>(savedCategory, HttpStatus.OK);

	}

	/**********************************
	 * DELETE MAPPINGS END
	 ************************************/
}
