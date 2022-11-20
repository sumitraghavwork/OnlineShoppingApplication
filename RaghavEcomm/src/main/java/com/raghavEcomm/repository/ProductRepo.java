package com.raghavEcomm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.raghavEcomm.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

	@Query("from Product p where p.productPrice <=:price")
	public List<Product> getProductsUnderPrice(@Param("price") Integer price);

	@Query("from Product p where p.productPrice >=:fromPrice AND p.productPrice <=:toPrice")
	public List<Product> getProductsFromPriceToPrice(@Param("fromPrice") Integer fromPrice,
			@Param("toPrice") Integer toPrice);

}
