package com.raghavEcomm.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.raghavEcomm.model.Customer;
import com.raghavEcomm.model.Feedback;
import com.raghavEcomm.model.Order;

@Repository
public interface FeedbackRepo extends JpaRepository<Feedback, Integer> {
	
	public List<Feedback> findByCustomer(Customer customer);
	
	public List<Feedback> findByOrder(Order order);
	
	public List<Feedback> findByOverallRating(Integer overallRating);
	
	public List<Feedback> findByComments(String comments);
	
	public List<Feedback> findByDriverRating(Integer driverRating);
	
	public List<Feedback> findByFeedbackdate(LocalDate feedbackdate);
	
	public List<Feedback> findByServiceRating(Integer serviceRating);
	
	
}
