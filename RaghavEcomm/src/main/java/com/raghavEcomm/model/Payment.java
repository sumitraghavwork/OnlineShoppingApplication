package com.raghavEcomm.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer paymentId;
	
	private Boolean paid;
	
	private Integer amount;
	
	private LocalDate paymentDate;
	
	@OneToOne
	@JsonIgnore
	private Card card;
	
	@OneToOne
	@JsonIgnore
	private Order order;
	
}
