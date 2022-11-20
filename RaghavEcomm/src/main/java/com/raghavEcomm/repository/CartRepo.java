package com.raghavEcomm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.raghavEcomm.model.Cart;

@Repository
public interface CartRepo extends JpaRepository<Cart, Integer> {

	@Query("Select c.cartValue from Cart c where c.cartId =:cid")
	public Integer getCartValueFromCartByCartId(@Param("cid") Integer cid);
}
