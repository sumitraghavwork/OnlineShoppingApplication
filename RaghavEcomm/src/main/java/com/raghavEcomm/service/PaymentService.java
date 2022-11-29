package com.raghavEcomm.service;

import java.util.List;

import com.raghavEcomm.exceptions.CardException;
import com.raghavEcomm.exceptions.LoginException;
import com.raghavEcomm.exceptions.OrderException;
import com.raghavEcomm.exceptions.PaymentException;
import com.raghavEcomm.exceptions.UserException;
import com.raghavEcomm.model.Payment;

public interface PaymentService {

	public Payment makePayment(Integer orderId, Integer cardId, String customerKey)
			throws LoginException, UserException, OrderException, CardException;

	public String cancelPayment(Integer orderId, String customerKey)
			throws LoginException, UserException, OrderException;

	public Payment getPaymentDetailsByPaymentId(Integer paymentId, String customerKey)
			throws LoginException, UserException, OrderException, PaymentException;

	public Payment getPaymentDetailsByOrderId(Integer orderId, String customerKey)
			throws OrderException, LoginException, UserException, PaymentException;

	public List<Payment> getAllPaymentOfCustomerByCustomerId(String customerKey) throws UserException, LoginException, PaymentException;

}
