package com.raghavEcomm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.raghavEcomm.model.Cart;
import com.raghavEcomm.model.Customer;

@Repository
public interface CartRepo extends JpaRepository<Cart, Integer> {

	@Query("Select c.cartValue from Cart c where c.cartId =:cid")
	public Integer getCartValueFromCartByCartId(@Param("cid") Integer cid);

	public List<Cart> findByCartValue(Integer cartValue);

	public List<Cart> findByCustomer(Customer customer);

}
