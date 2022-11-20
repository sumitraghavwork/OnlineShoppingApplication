package com.raghavEcomm.model;

import java.time.LocalDate;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderId;

	private String orderStatus;

	@OneToOne
	@JsonIgnore
	private Customer customer;

	private LocalDate orderDate;

	@OneToMany
	@JsonIgnore
	private Map<Product, Integer> products;

	@OneToOne
	@JsonIgnore
	private Address shippingAddress;

	@OneToOne
	@JsonIgnore
	private Payment payment;
}
