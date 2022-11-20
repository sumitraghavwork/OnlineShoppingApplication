package com.raghavEcomm.service;

import java.util.List;

import com.raghavEcomm.exceptions.FeedbackException;
import com.raghavEcomm.exceptions.LoginException;
import com.raghavEcomm.exceptions.OrderException;
import com.raghavEcomm.exceptions.UserException;
import com.raghavEcomm.model.Feedback;

public interface FeedbackService {

	public Feedback addFeedBack(Feedback feedback, String key, Integer orderId)
			throws FeedbackException, UserException, OrderException;

	public Feedback updateFeedBack(Feedback feedback, String key, Integer feedbackId)
			throws FeedbackException, LoginException, UserException;

	public Feedback viewFeedBack(Integer feedbackId) throws FeedbackException;

	public List<Feedback> viewAllFeedBack() throws FeedbackException;

	public Feedback deleteFeedBack(String key, Integer feedbackId)
			throws FeedbackException, LoginException, UserException;

}
