package com.raghavEcomm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.raghavEcomm.model.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {

}
